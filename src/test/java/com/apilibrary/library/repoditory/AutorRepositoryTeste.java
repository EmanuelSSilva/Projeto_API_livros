package com.apilibrary.library.repoditory;

import com.apilibrary.library.model.Autor;
import com.apilibrary.library.model.Generos;
import com.apilibrary.library.model.Livro;
import com.apilibrary.library.repository.AutorRepository;
import com.apilibrary.library.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTeste {

    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvateste(){
        Autor autor = new Autor();
        autor.setNome("Douglas");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1988,2,21));

        var autorsalvo = repository.save(autor);
        System.out.println(" Autor Salvo " + autorsalvo);
    }

    @Test
    public void atualizarTeste(){
       var id = UUID.fromString("b84f5e00-5126-4fde-aa2c-d824c10fdfd2");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autoEncontrado = possivelAutor.get();
            System.out.println(autoEncontrado);

            autoEncontrado.setDataNascimento(LocalDate.of(1988, 2, 21));
            repository.save(autoEncontrado);
        }

    }

    @Test
    public void buscaTodos(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTeste(){
        System.out.println("Contagem de autores " + repository.count());
    }

    @Test
    public void deletarTeste(){
        var id = UUID.fromString("56bcdd64-a27a-41b1-8695-66af588f8226");
        repository.deleteById(id);
    }

    @Test
    public void salvarAutorComLivros(){
        Autor autor = new Autor();
        autor.setNome("Gorge");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1888,2,27));

        Livro livro = new Livro();
        livro.setIsbn("90887-88755");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(Generos.CIENCIA);
        livro.setTitulo("Biologia");
        livro.setDataPublicacao(LocalDate.of(1901, 3, 15));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("90887-80000");
        livro2.setPreco(BigDecimal.valueOf(250));
        livro2.setGenero(Generos.ROMANCE);
        livro2.setTitulo("Amor Profundo");
        livro2.setDataPublicacao(LocalDate.of(1921, 8, 23));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());


    }

    @Test
    public void listarlirosAutor(){
        var id = UUID.fromString("d17c1952-73b7-4d5b-9cf7-be11aed05e8c");
        var autor = repository.findById(id).get();
        List<Livro> livroLista = livroRepository.findByAutor(autor);
        autor.setLivros(livroLista);

        autor.getLivros().forEach(System.out::println);

    }


}
