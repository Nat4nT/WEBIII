package br.edu.ifpr.foz.ifprstore.controllers;

import br.edu.ifpr.foz.ifprstore.models.*;
import br.edu.ifpr.foz.ifprstore.repositories.AuthorRepository;
import br.edu.ifpr.foz.ifprstore.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns= {"/books/create"})
public class BookCreateController extends HttpServlet {

    BookRepository repo = new BookRepository();
    AuthorRepository repoAuthor = new AuthorRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = repo.getAllBooks();
        List<Author> author = repoAuthor.getAll();


        req.setAttribute("books", books);
        req.setAttribute("authors", author);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-create.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("field_name");
        LocalDate date = LocalDate.parse(req.getParameter("field_date"));
        String status  = req.getParameter("status");
        Integer AuthorId = Integer.valueOf(req.getParameter("field_author"));



        Author author = new Author();
        author.setId(AuthorId);

        Book book = new Book();
        book.setName(name);
        book.setDate(date);
        book.setStatus(BookStatus.valueOf(status.toUpperCase()));
        book.setAuthor(author);

        repo.insert(book);

        resp.sendRedirect(req.getContextPath() + "/books");

    }
}
