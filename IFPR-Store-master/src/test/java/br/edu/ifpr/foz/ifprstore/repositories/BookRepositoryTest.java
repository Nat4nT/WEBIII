package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.models.Author;
import br.edu.ifpr.foz.ifprstore.models.Book;
import br.edu.ifpr.foz.ifprstore.models.BookStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class BookRepositoryTest {


    @Test
    public void deveExibirUmaListaDeLivros(){

        BookRepository repository = new BookRepository();

        List<Book> books = repository.getAllBooks();

        for (Book s: books) {
            System.out.println(s.getName());
        }
    }

    @Test
    public void deveInserirUmRegistroNaTabelaBook(){

        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        Author author = authorRepository.getById(1);

        Book FakeBook = new Book();
        FakeBook.setName("Hobbit - Uma Aventura Inesperada");
        FakeBook.setDate(LocalDate.of(2024, 8, 5));
        FakeBook.setStatus(BookStatus.valueOf("AVAILABLE"));
        FakeBook.setAuthor(author);

        Book book = bookRepository.insert(FakeBook);

    }

    @Test
    public void deveAtualizarOStatusDeUmLivro(){

        BookRepository repository = new BookRepository();

        repository.updateStatus(1, "unavailable");


    }


}
