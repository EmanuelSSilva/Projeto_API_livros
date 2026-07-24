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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class LivroRepositoryTeste {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void  salvarTeste(){

        Livro livro = new Livro();
        livro.setIsbn("90887-88755");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(Generos.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 3, 15));

        Autor autor = autorRepository.findById(UUID.fromString("6aaa119f-b90d-45a4-bf94-e24a47ac9b92")).orElse(null);

        livro.setAutor(autor);
        repository.save(livro);

    }

    @Test
    public void salveCascataTeste(){

        Livro livro = new Livro();
        livro.setIsbn("90887-88852");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(Generos.BIOGRAFIA);
        livro.setTitulo("Selva");
        livro.setDataPublicacao(LocalDate.of(1945, 3, 22));

        Autor autor = new Autor();
        autor.setNome("Eduard Bonas");
        autor.setNacionalidade("Americado");
        autor.setDataNascimento(LocalDate.of(1938,9,12));

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    public void salvarSemCascateTeste(){

        Livro livro = new Livro();
        livro.setIsbn("90887-88755");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(Generos.FANTASIA);
        livro.setTitulo("Avatar");
        livro.setDataPublicacao(LocalDate.of(1980, 3, 15));

        Autor autor = new Autor();
        autor.setNome("Hambo");
        autor.setNacionalidade("Americado");
        autor.setDataNascimento(LocalDate.of(1998,12,16));

        autorRepository.save(autor);

        livro.setAutor(autor);
        repository.save(livro);
    }

    @Test
    public void atualizarAutorDoLivroTeste(){

        var idLivro = repository.findById(UUID.fromString("629a62e9-ab06-41b9-9112-c4c4fe3ef65c")).orElse(null);

        var emanuel = autorRepository.findById(UUID.fromString("b84f5e00-5126-4fde-aa2c-d824c10fdfd2")).orElse(null);

        idLivro.setAutor(emanuel);

        repository.save(idLivro);

    }

    @Test
    public void deletarTeste(){
        var idLivro = UUID.fromString("583d642a-cb96-4153-b8fe-6c010b65ac9f");
        repository.deleteById(idLivro);
    }

    @Test
    public void deletarCascade(){
        var idLivro = UUID.fromString("629a62e9-ab06-41b9-9112-c4c4fe3ef65c");
        repository.deleteById(idLivro);
    }

    @Test
    @Transactional
    public void buscarLivroTeste(){

        UUID id = UUID.fromString("d9d35ee3-1424-428f-a035-21d41ef832df");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("livro");
        System.out.println(livro.getTitulo());

        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());

    }

    @Test
    public void pesquisaPorTituloTeste(){
        List<Livro> listaTitulo = repository.findByTitulo("Avatar");
        listaTitulo.forEach(System.out::println);
    }

    @Test
    public void pesquisarPorIsbnTeste(){
        Optional<Livro> listaIsbn = repository.findByIsbn("90887-88755");
        listaIsbn.ifPresent(System.out::println);
    }

    @Test
    public void PesquisaLivroTituloEPrecoTeste(){
        List<Livro> tituloPreco = repository.findByTituloAndPreco("Avatar", BigDecimal.valueOf(100.00));
        tituloPreco.forEach(System.out::println);
    }

    @Test
    public void PesquisaLivroTituloEPrecoOrdenadosTeste(){
        List<Livro> resultado= repository.listarTodosOrdenados();
        resultado.forEach(System.out::println);
    }

    @Test
    public void listaOsAutoresDoLivroTeste(){
        var listaAutor = repository.listarAutoresDosLivros();
        listaAutor.forEach(System.out::println);
    }

    @Test
    public void listaOsdiferentesDosLivrosTeste(){
        var listaAutor = repository.listaNomesDiferentesDeLivros();
        listaAutor.forEach(System.out::println);
    }
}
