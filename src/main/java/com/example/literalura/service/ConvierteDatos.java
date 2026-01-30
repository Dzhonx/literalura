package com.example.literalura.service;

import com.example.literalura.dto.RespuestaGutendex;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConvierteDatos {

    private final ObjectMapper mapper = new ObjectMapper();

    public RespuestaGutendex obtenerDatosLibro(String json) {
        try {
            return mapper.readValue(json, RespuestaGutendex.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON", e);
        }
    }
}
