package br.edu.ifpr.foz.ifprstore.controllers;

import br.edu.ifpr.foz.ifprstore.repositories.BookRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import br.edu.ifpr.foz.ifprstore.models.Book;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"", "/books"})
public class BookController extends HttpServlet {
   private BookRepository repository;
    public BookController() {
        repository= new BookRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> book = repository.getAllBooks();
        RequestDispatcher dispatcher = req.getRequestDispatcher("books.jsp");
        req.setAttribute("books", book);
        dispatcher.forward(req, resp);

    }
}
