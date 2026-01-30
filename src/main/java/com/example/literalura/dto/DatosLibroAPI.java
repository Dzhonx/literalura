package com.example.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroAPI(
        String title,

        List<DatosAutorAPI> authors,

        List<String> languages,

        @JsonAlias("download_count")
        Integer numeroDescargas
) {}

