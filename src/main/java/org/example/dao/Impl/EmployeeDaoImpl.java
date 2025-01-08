package org.example.dao.Impl;

import org.example.JDConnection;
import org.example.dao.EmployeeDao;
import org.example.models.Employee;
import org.example.models.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public void createEmployee() {
        String sql = "CREATE TABLE IF NOT EXISTS employees (" +
                "id SERIAL PRIMARY KEY, " +
                "first_name VARCHAR, " +
                "last_name VARCHAR, " +
                "age INT, " +
                "email VARCHAR, " +
                "job_id INT, " +
                "FOREIGN KEY (job_id) REFERENCES jobs(id)"+
                ")";
        try (Statement statement = JDConnection. getConnection().createStatement()){
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("table created successfully: " + e.getMessage());
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (first_name, last_name, age, email, job_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getFirstName()); // first_name орнотуу
            statement.setString(2, employee.getLastName()); // last_name орнотуу
            statement.setInt(3, employee.getAge()); // age орнотуу
            statement.setString(4, employee.getEmail()); // email орнотуу
            statement.setInt(5, employee.getJob_Id());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("added successFull: " + e.getMessage());
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS employees";

        try (Statement statement=JDConnection.getConnection().createStatement()){
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("table deleted successfully: " + e.getMessage());
        }
    }

    @Override
    public void cleanTable() {
        String sql = "DELETE FROM employees";
        try (Statement statement = JDConnection.getConnection().createStatement()){
             statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("clean table successfully: " + e.getMessage());
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, age = ?, email = ?, job_id = ? WHERE id = ?";
        try{
            PreparedStatement preparedStatement = JDConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setInt(5, employee.getJob_Id());
            preparedStatement.setLong(6, id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("update successFully: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM employees";
        List<Employee> employees = new ArrayList<>();

        try (Statement statement = JDConnection.getConnection().createStatement()){
             ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJob_Id(resultSet.getInt("job_id"));

                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении всех сотрудников: " + e.getMessage());
        }

        return employees;

    }

    @Override
    public Employee findByEmail(String email) {
        String sql = "SELECT * FROM employees WHERE email = ?";
        Employee employee = null;

        try {
            PreparedStatement preparedStatement = JDConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String employeeEmail = resultSet.getString("email");
                int jobId = resultSet.getInt("job_id");

                employee = new Employee(firstName, lastName, age, employeeEmail, jobId);
                employee.setId(id);
            }
             preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Ошибка при поиске сотрудника по email: " + e.getMessage());
        }

        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
       return null;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        String sql = "SELECT * FROM employees e JOIN jobs j ON e.job_id = j.id WHERE j.position = ?";
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = JDConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, position);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int age = rs.getInt("age");
                    String email = rs.getString("email");
                    int jobId = rs.getInt("job_id");

                    Employee employee = new Employee(firstName, lastName, age, email, jobId);
                    employee.setId(id);
                    employees.add(employee);
                }
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при получении сотрудников по позиции: " + e.getMessage());
        }

        return employees;
    }

    }

