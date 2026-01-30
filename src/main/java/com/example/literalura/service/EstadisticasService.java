package com.example.literalura.service;

import com.example.literalura.model.Idioma;
import com.example.literalura.repository.AutorRepository;
import com.example.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {

    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    public EstadisticasService(LibroRepository libroRepository,
                               AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    // Cantidad de libros por idioma
    public void mostrarCantidadLibrosPorIdioma(Idioma idioma) {
        long cantidad = libroRepository.findByIdioma(idioma).size();
        System.out.println("Cantidad de libros en " + idioma + ": " + cantidad);
    }

    // Autores vivos en un año específico
    public void listarAutoresVivosEnAnio(int anio) {
        var autores = autorRepository.findAll().stream()
                .filter(a -> a.getAnioNacimiento() != null && a.getAnioNacimiento() <= anio)
                .filter(a -> a.getAnioFallecimiento() == null || a.getAnioFallecimiento() >= anio)
                .toList();

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en ese año");
        } else {
            System.out.println("Autores vivos en " + anio + ":");
            autores.forEach(a -> System.out.println(" - " + a.getNombre()));
        }
    }
}
