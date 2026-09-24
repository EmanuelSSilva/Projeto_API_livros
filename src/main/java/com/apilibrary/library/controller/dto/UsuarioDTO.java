package com.apilibrary.library.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record UsuarioDTO(
        @NotBlank(message = "Campo Obrigatorio")
        String login,
        @Email (message = "invalid")
        @NotBlank(message = "Campo Obrigatorio")
        String email,
        @NotBlank(message = "Campo Obrigatorio")
        String senha,
        List<String> roles)
        {
}
