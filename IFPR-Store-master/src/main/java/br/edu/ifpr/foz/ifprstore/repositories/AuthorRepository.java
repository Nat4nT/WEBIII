package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.foz.ifprstore.models.Author;
import br.edu.ifpr.foz.ifprstore.models.Book;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {
    private Connection conn;

    public AuthorRepository() {
        conn= ConnectionFactory.getConnection();
    }


    public List<Author> getAll() {
        List<Author> authors= new ArrayList<Author>();

        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from author ");

            while (resultSet.next()) {
                Author author = instantiateAuthor(resultSet);
                authors.add(author);
            }
            resultSet.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return authors;
    }
    public Author getById(Integer id) {

        Author author;

        String sql = "SELECT * " +
                "FROM author " +
                "WHERE id = ? " ;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                author = this.instantiateAuthor(resultSet);

            } else {
                throw new DatabaseException("Livro não encontrado");
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }

        return author;
    }
    public Author instantiateAuthor(ResultSet resultSet) throws SQLException {

        Author author = new Author();

        author.setId(resultSet.getInt("id"));
        author.setName(resultSet.getString("name"));

        return author;
    }
}
