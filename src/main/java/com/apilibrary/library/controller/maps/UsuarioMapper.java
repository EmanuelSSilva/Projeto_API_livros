package com.apilibrary.library.controller.maps;


import com.apilibrary.library.controller.dto.UsuarioDTO;
import com.apilibrary.library.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    Usuario toEntityy(UsuarioDTO dto);

}
