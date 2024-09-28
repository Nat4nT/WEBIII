package br.edu.ifpr.foz.ifprstore.repositories;


import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.foz.ifprstore.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookRepository {

    private Connection conn;

    public BookRepository() {
        conn = ConnectionFactory.getConnection();
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from book ");


            Map<Integer, Author> map = new HashMap<>();


            while (resultSet.next()) {

                Author author = map.get(resultSet.getInt("author_id"));
                if (author == null) {
                    author = instantiateAuthor(resultSet);
                    map.put(resultSet.getInt("author_id"), author);
                }
                Book book = instantiateBook(resultSet, author);
                books.add(book);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return books;
    }

    public Book insert(Book book) {
        String query = "INSERT INTO book (name, date, author_id, status) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, book.getName());
            statement.setDate(2, Date.valueOf(book.getDate()));
            statement.setInt(3, book.getAuthor().getId());
            statement.setString(4, book.getStatus().getStatus().toUpperCase());

            Integer rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                Integer id = generatedKeys.getInt(1);
                book.setId(id);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
        return book;
    }

    public void updateStatus(Integer bookId, String status) {

        String sql = "UPDATE book SET status =  ? WHERE id = ?";

        try {

            PreparedStatement statement = conn.prepareStatement(sql);//crl+alt+v
            statement.setString(1, status);
            statement.setInt(2, bookId);

            Integer rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Rows updated: " + rowsUpdated);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

    }

    public void update(Book book) {

        String sql = "UPDATE book SET " +
                "name = ?, " +
                "date = ?, " +
                "author_id = ? ," +
                "status = ? " +
                "WHERE (book.id = ?)";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setDate(2, Date.valueOf(book.getDate()));
            statement.setInt(3, book.getAuthor().getId());
            statement.setString(4, book.getStatus().getStatus().toUpperCase());
            statement.setInt(5, book.getId());

            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

    }

    public Author instantiateAuthor(ResultSet resultSet) throws SQLException {

        Author author = new Author();

        AuthorRepository authorRepository = new AuthorRepository();

        String name = authorRepository.getById(resultSet.getInt("author_id")).getName();

        author.setId(resultSet.getInt("author_id"));
        author.setName(name);

        return author;
    }

    public Book instantiateBook(ResultSet rs, Author author) throws SQLException {
        Book book = new Book();

        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setDate(rs.getDate("date").toLocalDate());
        book.setStatus(BookStatus.valueOf(rs.getString("status")));
        book.setAuthor(author);

        return book;
    }

    public void delete(Integer id) {

        String sql = "DELETE FROM book WHERE Id = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            Integer rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Rows deleted: " + rowsDeleted);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public List<Book> findByAuthor(Integer id) {

        List<Book> bookList = new ArrayList<>();

        Book book;
        Author author;

        String sql = "SELECT book.*,author.name as Author " +
                "FROM book INNER JOIN author " +
                "ON book.author_id = author.id " +
                "WHERE id = ? " +
                "ORDER BY book.name";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            Map<Integer, Author> map = new HashMap<>();

            while (resultSet.next()) {

                author = map.get(resultSet.getInt("author_id"));

                if (author == null) {
                    author = instantiateAuthor(resultSet);
                    map.put(resultSet.getInt("DepartmentId"), author);
                }

                book = this.instantiateBook(resultSet, author);

                bookList.add(book);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return bookList;

    }


    public Book getById(Integer id) {

        Book book;
        Author author;

        String sql = "SELECT book.*,author.name as Author " +
                "FROM book INNER JOIN author " +
                "ON book.author_id = author.id " +
                "WHERE book.id = ? " +
                "ORDER BY book.name";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                author = this.instantiateAuthor(resultSet);
                book = this.instantiateBook(resultSet, author);

            } else {
                throw new DatabaseException("Livro não encontrado");
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return book;
    }
}

