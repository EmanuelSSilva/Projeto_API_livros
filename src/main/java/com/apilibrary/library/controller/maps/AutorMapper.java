package com.apilibrary.library.controller.maps;

import com.apilibrary.library.controller.dto.AutorDTO;
import com.apilibrary.library.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AutorMapper {

    Autor toEntity(AutorDTO dto);

    AutorDTO toDTO(Autor autor);
}
