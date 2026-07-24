package com.apilibrary.library.repository.specs;

import com.apilibrary.library.model.Generos;
import com.apilibrary.library.model.Livro;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpesc {

    public static Specification<Livro> isbnEqual(String isbn){
        return (root, query, cb) -> cb.equal(root.get("isbn"), isbn);
    }

    public static Specification<Livro> tituloLike(String titulo){
        return (root, query, cb) -> cb.like(cb.upper(root.get("titulo")),"%" + titulo.toUpperCase() + "%");
    }

    public static Specification<Livro> generoEqual(Generos genero){
        return ((root, query, cb) -> cb.equal(root.get("genero"), genero));
    }

    public static Specification<Livro> anoPublicacaoEqual(Integer anoPublicacao){
        return (root, query, cb) ->cb.equal(cb.function("to_char", String.class, root.get("dataPublicacao")), cb.literal("YYYY"));
    }

    public static Specification<Livro> nomeAutorLike(String nome){
        return (root, query, cb) -> {

            Join<Object, Object> joinAutor = root.join("autor", JoinType.LEFT);
            return cb.like( cb.upper(joinAutor.get("nome")), "%" + nome.toUpperCase() + "%");

            // forma simples
            //  return cb.like(cb.upper(root.get("autor").get("nome")), "%" + nome.toUpperCase() + "%");
        };
    }

}
