package com.example.literalura.model;

public enum Idioma {
    ES("es"),
    EN("en"),
    FR("fr"),
    PT("pt");

    private String codigoAPI;

    Idioma(String codigoAPI) {
        this.codigoAPI = codigoAPI;
    }

    public static Idioma desdeCodigo(String codigo) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.codigoAPI.equalsIgnoreCase(codigo)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Idioma no encontrado: " + codigo);
    }
}
