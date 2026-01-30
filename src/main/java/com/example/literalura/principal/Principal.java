package com.example.literalura.principal;

import com.example.literalura.service.ConsumoAPIService;
import com.example.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final LibroService libroService;

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    @Override
    public void run(String... args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    1 - Buscar libro por título
                    2 - Listar libros
                    3 - Listar autores
                    4 - Autores vivos en un año
                    5 - Libros por idioma
                    0 - Salir
                    """);

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.println("Escribe el título del libro:");
                    String titulo = teclado.nextLine();

                    var respuesta = libroService.buscarLibroPorTitulo(titulo);

                    if (!respuesta.results().isEmpty()) {
                        libroService.guardarLibroDesdeAPI(respuesta.results().get(0));
                    } else {
                        System.out.println("No se encontró ningún libro");
                    }
                }

                case 2 -> libroService.listarLibrosGuardados();

                case 3 -> libroService.listarAutores();

                case 4 -> {
                    System.out.println("Ingresa el año:");
                    int anio = teclado.nextInt();
                    teclado.nextLine();
                    libroService.listarAutoresVivosEnAnio(anio);
                }

                case 5 -> {
                    System.out.println("Ingresa el idioma (ES, EN, FR, PT):");
                    String idioma = teclado.nextLine().toUpperCase();
                    libroService.listarLibrosPorIdioma(idioma);
                }

                case 0 -> System.out.println("Cerrando aplicación...");

                default -> System.out.println("Opción inválida!!!");
            }
        }
    }
}

