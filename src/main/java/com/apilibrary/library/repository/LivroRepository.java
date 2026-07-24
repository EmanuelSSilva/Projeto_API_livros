package com.apilibrary.library.repository;

import com.apilibrary.library.model.Autor;
import com.apilibrary.library.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID>, JpaSpecificationExecutor <Livro> {

    //Query method
    List<Livro> findByAutor(Autor autor);

    List<Livro> findByTitulo(String titulo);

    Optional<Livro> findByIsbn(String isbn);

    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //JPQL -> referencia as entidades e propriedades
    @Query("select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenados();

    // select a.* from tb_livro l join autor a on a.id = l.id_autor
    @Query("select a from Livro l join l.autor a order by l.autor")
    List<Autor> listarAutoresDosLivros();


    //select distinct l.* from tb_livro
    @Query("select distinct l.titulo from Livro l")
    List<String> listaNomesDiferentesDeLivros();

    boolean existsByAutor(Autor autor);
}
