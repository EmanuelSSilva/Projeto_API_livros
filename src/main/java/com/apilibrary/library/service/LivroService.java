package com.apilibrary.library.service;


import com.apilibrary.library.model.Generos;
import com.apilibrary.library.model.Livro;
import com.apilibrary.library.repository.LivroRepository;
import com.apilibrary.library.validator.LivroValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.apilibrary.library.repository.specs.LivroSpesc.*;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
    private final LivroValidator livroValidator;

    public Livro salvar(Livro livro){
        livroValidator.validar(livro);
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro){
        livroRepository.delete(livro);
    }


    public Page<Livro> pesquisa(String isbn, String titulo, String nomeAutor, Generos genero, Integer anoPublicacao, Integer pagina, Integer tamanhoPagina){

        Specification<Livro> specs = Specification.where((root, query, cb) -> cb.conjunction());
        if(isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }
        if(titulo != null){
            specs = specs.and(tituloLike(titulo));
        }
        if(genero != null){
            specs = specs.and(generoEqual(genero));
        }
        if(anoPublicacao != null){
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }
        if(nomeAutor != null){
            specs = specs.and(nomeAutorLike(nomeAutor));
        }

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina);

        return livroRepository.findAll(specs, pageRequest);
    }


    public void atualizar(Livro livro) {
        if (livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar, é necessario que o Livro já esteja salvo na base.");
        }
        livroValidator.validar(livro);
        livroRepository.save(livro);
    }
}
