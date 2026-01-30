package com.example.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutorAPI(
        String name,

        @JsonAlias("birth_year")
        Integer anioNacimiento,

        @JsonAlias("death_year")
        Integer anioFallecimiento
) {}
