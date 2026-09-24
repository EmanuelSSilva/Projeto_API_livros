package com.apilibrary.library.controller;


import com.apilibrary.library.controller.dto.UsuarioDTO;
import com.apilibrary.library.controller.maps.UsuarioMapper;
import com.apilibrary.library.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid UsuarioDTO dto){
        var usuario = usuarioMapper.toEntityy(dto);
        usuarioService.salvar(usuario);
    }

}
