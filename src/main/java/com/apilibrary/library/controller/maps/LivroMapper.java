package com.apilibrary.library.controller.maps;

import com.apilibrary.library.controller.dto.CadastrolivroDTO;
import com.apilibrary.library.controller.dto.ResultadoPesquisaLivroDTO;
import com.apilibrary.library.model.Livro;
import com.apilibrary.library.repository.AutorRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = AutorMapper.class)
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "autor", expression = "java(autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntityLivro (CadastrolivroDTO dto);


    public abstract ResultadoPesquisaLivroDTO toDTO(Livro livro);


}
