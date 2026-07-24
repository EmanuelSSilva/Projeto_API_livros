package com.apilibrary.library.controller.dto;

import com.apilibrary.library.model.Generos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        Generos genero,
        BigDecimal preco,
        AutorDTO autor) {

}
