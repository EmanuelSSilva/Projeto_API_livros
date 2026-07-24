package com.apilibrary.library.controller.dto;

import com.apilibrary.library.model.Autor;
import com.apilibrary.library.model.Generos;
import com.apilibrary.library.model.Livro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CadastrolivroDTO(
        @ISBN
        @NotBlank(message = "Campo obrigatorio")
        String isbn,
        @NotBlank(message = "Campo obrigatorio")
        String titulo,
        @NotNull(message = "Campo obrigatorio")
        @Past(message = "Não pode ser uma passada")
        LocalDate dataPublicacao,
        Generos genero,
        BigDecimal preco,
        @NotNull(message = "Campo obrigatorio")
        UUID idAutor) {


}
