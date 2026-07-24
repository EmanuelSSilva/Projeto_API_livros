package com.apilibrary.library.controller.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,
        @NotBlank(message = "Campo obrigatorio")
        String nome,
        @NotNull(message = "Campo obrigatorio")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatorio")
        String nacionalidade) {



}
