package com.apilibrary.library.validator;

import com.apilibrary.library.exceptions.RegistroDuplicadoException;
import com.apilibrary.library.model.Autor;
import com.apilibrary.library.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository repository;

    public AutorValidator(AutorRepository repository) {
        this.repository = repository;
    }

    public void validar(Autor autor){
        if(existeAutorCadastrado(autor)){
            throw new RegistroDuplicadoException("Autor já cadastrado");
        }
    }

    public boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = repository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
        if(autor.getId() == null){
            return autorEncontrado.isPresent();
        }
        return autorEncontrado
                .map(a -> !a.getId().equals(autor.getId()))
                .orElse(false);
    }
}