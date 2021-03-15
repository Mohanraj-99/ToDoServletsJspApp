package com.mohanraj.ToDo_Application.dao;

import com.mohanraj.ToDo_Application.model.Todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDao  {

    public static void updateTodo(Todo todo) throws ClassNotFoundException{

        String sql = "update todos "
                + "set topic=?, description=?, status=? "
                + "where id=?";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(4, todo.getId());
            preparedStatement.setString(1, todo.getTopic());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setString(3, todo.getStatus());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public static void deleteTodo(String todoID) throws ClassNotFoundException{

        String sql = "delete from todos where id=?";
        int id = Integer.parseInt(todoID);

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

    }

    public static Todo getTodo(String todoID) throws ClassNotFoundException{

        String sql = "select * from todos where id=?";
        Todo todo = null;
        int id = Integer.parseInt(todoID);
        ResultSet myRs = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            myRs = preparedStatement.executeQuery();
            if (myRs.next()) {
                String topic = myRs.getString("topic");
                String description = myRs.getString("description");
                String status = myRs.getString("status");

                // use the studentId during construction
                todo = new Todo (id, topic, description, status);
            }
            else {
                throw new Exception("Could not find todo id: " + todoID);
            }

            return todo;

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return todo;
    }

    public static List<Todo> getTodos() throws ClassNotFoundException{

        String sql = "select * from todos";
        ResultSet myRs = null;
        List<Todo> todos = new ArrayList<>();
        Todo todo = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            myRs = preparedStatement.executeQuery();
            while (myRs.next()) {

                // retrieve data from result set row
                int id = myRs.getInt("id");
                String topic = myRs.getString("topic");
                String description = myRs.getString("description");
                String status = myRs.getString("status");

                // create new student object
                todo = new Todo(id, topic, description, status);

                // add it to the list of students
                todos.add(todo);
            }
            return todos;

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return todos;
    }

    public static void addTodo(Todo todo) throws ClassNotFoundException {
        String sql = "insert into todos "
                + "(topic, description, status) "
                + "values (?, ?, ?)";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, todo.getTopic());
            preparedStatement.setString(2, todo.getDescription());
            preparedStatement.setString(3, todo.getStatus());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }


    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
