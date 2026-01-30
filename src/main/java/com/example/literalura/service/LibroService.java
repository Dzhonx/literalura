package com.example.literalura.service;

import com.example.literalura.dto.DatosAutorAPI;
import com.example.literalura.dto.DatosLibroAPI;
import com.example.literalura.dto.RespuestaGutendex;
import com.example.literalura.model.Autor;
import com.example.literalura.model.Idioma;
import com.example.literalura.model.Libro;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class LibroService {

    private final ConsumoAPIService consumoAPI;
    private final ConvierteDatos convierteDatos;
    private final LibroRepository libroRepo;
    private final AutorRepository autorRepo;

    public LibroService(ConsumoAPIService consumoAPI,
                        ConvierteDatos convierteDatos,
                        LibroRepository libroRepo,
                        AutorRepository autorRepo) {
        this.consumoAPI = consumoAPI;
        this.convierteDatos = convierteDatos;
        this.libroRepo = libroRepo;
        this.autorRepo = autorRepo;
    }

    public RespuestaGutendex buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo.replace(" ", "%20");
        String json = consumoAPI.obtenerDatos(url);
        return convierteDatos.obtenerDatosLibro(json);
    }

    public void guardarLibroDesdeAPI(DatosLibroAPI datos) {
        if (libroRepo.existsByTituloIgnoreCase(datos.title())) {
            System.out.println("El libro ya existe en la base de datos!!!");
            return;
        }
        DatosAutorAPI autorAPI = datos.authors().get(0);
        Autor autor = new Autor(
                autorAPI.name(),
                autorAPI.anioNacimiento(),
                autorAPI.anioFallecimiento()
        );
        Idioma idioma = Idioma.desdeCodigo(datos.languages().get(0));
        Libro libro = new Libro(
                datos.title(),
                idioma,
                datos.numeroDescargas(),
                autor
        );
        libroRepo.save(libro);
        System.out.println("Libro guardado correctamente");
    }

    public void listarLibrosGuardados() {
        var libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros guardados todavía!!!");
            return;
        }
        libros.forEach(libro -> {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Descargas: " + libro.getNumeroDescargas());
            System.out.println("---------------------------");
        });
    }

    public void listarAutores() {
        var autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados!!!");
            return;
        }
        autores.forEach(autor -> {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Nacimiento: " + autor.getAnioNacimiento());
            System.out.println("✝Fallecimiento: " + autor.getAnioFallecimiento());
            System.out.println("---------------------------");
        });
    }

    public void listarAutoresVivosEnAnio(int anio) {
        var autores = autorRepo
                .findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año!!!");
            return;
        }
        autores.forEach(autor -> {
            System.out.println("Autor: " + autor.getNombre() +
                    " (" + autor.getAnioNacimiento() + " - " + autor.getAnioFallecimiento() + ")");
        });
    }

    public void listarLibrosPorIdioma(String codigoIdioma) {
        Idioma idioma = Idioma.desdeCodigo(codigoIdioma);
        var libros = libroRepo.findByIdioma(idioma);
        if (libros.isEmpty()) {
            System.out.println("No hay libros en ese idioma!!!");
            return;
        }
        libros.forEach(libro -> {
            System.out.println("Libro: " + libro.getTitulo() + " - " + libro.getAutor().getNombre());
        });
    }
}
