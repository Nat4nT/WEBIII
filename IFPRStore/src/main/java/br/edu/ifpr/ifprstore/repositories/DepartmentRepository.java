package br.edu.ifpr.ifprstore.repositories;

import br.edu.ifpr.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.ifprstore.exceptions.DataBaseIntegrity;
import br.edu.ifpr.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.ifprstore.models.Department;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DepartmentRepository {
    Connection connection;

    public DepartmentRepository(){
        connection = ConnectionFactory.getConnection();
    }

    public void delete(int id){

        String sql = "DELETE FROM department WHERE Id =?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();
            if(rowsDeleted >0) {
                System.out.println("Row Deleted: " + rowsDeleted);
            }
        } catch (Exception e) {
            throw new DataBaseIntegrity(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public Department insert(Department department) {
        String sql = "INSERT INTO department (Name) " +
                "VALUES(?)";


        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, department.getName());

            Integer rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet id = statement.getGeneratedKeys();

                id.next();
                Integer departmentId = id.getInt(1);
                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Id: " + departmentId);

                department.setId(departmentId);
            }
            department.getId();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
        return department;
    }

    public void updateDepartmentName(Integer departmentId, String newName) {

        String sql = "UPDATE department SET Name = ? WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newName);
            statement.setInt(2, departmentId);

            Integer rowsUpdate = statement.executeUpdate();
            if (rowsUpdate > 0) {
                System.out.println("Rows updated:" + rowsUpdate);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

    public List<Department> listAllDepartments() {
        List<Department> departmentsList = new ArrayList<>();

        Department department;

        String sql = "SELECT * FROM department ORDER BY Name";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    department = new Department();
                    department.setId(resultSet.getInt("Id"));
                    department.setName(resultSet.getString("Name"));
                    departmentsList.add(department);
                    }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return departmentsList;
    }

    public Department getById(Integer id) {

        Department department;

        String sql = "SELECT * FROM department WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                department = new Department();
                department.setId(resultSet.getInt("Id"));
                department.setName(resultSet.getString("Name"));

            } else {
                throw new DatabaseException("Departamento não encontrado");
            }
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
        finally {
            ConnectionFactory.closeConnection();
        }
        return department;
    }

    public List<Department> findByWord(String word) {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department WHERE Name LIKE ? ORDER BY Name";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + word + "%");

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("Id"));
                department.setName(resultSet.getString("Name"));
                departments.add(department);
            }

        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

        return departments;
    }

    public List<Department> findDepartmentsWithoutSellers() {
            List<Department> departmentsList = new ArrayList<>();
            String sql = "SELECT d.* FROM department d " +
                    "LEFT JOIN seller s ON d.Id = s.DepartmentId " +
                    "WHERE s.Id IS NULL ORDER BY d.Name";

            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Department department = new Department();
                    department.setId(resultSet.getInt("Id"));
                    department.setName(resultSet.getString("Name"));
                    departmentsList.add(department);
                }

            } catch (SQLException e) {
                throw new DatabaseException(e.getMessage());
            } finally {
                ConnectionFactory.closeConnection();
            }

            return departmentsList;
        }
}
