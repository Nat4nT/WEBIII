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

@WebServlet("/books/update")
public class BookUpdateController extends HttpServlet {

    BookRepository repository = new BookRepository();
    AuthorRepository authorRepository = new AuthorRepository();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id =  Integer.valueOf(req.getParameter("id"));

        List<Author> authors = authorRepository.getAll();
        req.setAttribute("authors", authors);

        Book book = repository.getById(id);
        req.setAttribute("book", book);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/books-update.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer id = Integer.valueOf(req.getParameter("field_id"));
        String name = req.getParameter("field_name");
        LocalDate date = LocalDate.parse(req.getParameter("field_date"));
        String status  = req.getParameter("status");
        Integer AuthorId = Integer.valueOf(req.getParameter("field_author"));

        Author author = new Author();
        author.setId(AuthorId);

        Book book = new Book();

        book.setId(id);
        book.setName(name);
        book.setDate(date);
        book.setStatus(BookStatus.valueOf(status.toUpperCase()));
        book.setAuthor(author);


        repository.update(book);

        resp.sendRedirect(req.getContextPath() + "/books");

    }
}
