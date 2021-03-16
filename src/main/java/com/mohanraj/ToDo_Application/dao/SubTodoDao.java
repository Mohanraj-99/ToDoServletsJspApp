package com.mohanraj.ToDo_Application.dao;

import com.mohanraj.ToDo_Application.model.SubTodo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubTodoDao  {

    public static void updateSubTodo(SubTodo subTodo) throws ClassNotFoundException{

        String sql = "update sub_todos "
                + "set sub_topic=?, description=?, status=? "
                + "where id=? and parent_id=?";
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(4, subTodo.getId());
            preparedStatement.setString(1, subTodo.getSubTopic());
            preparedStatement.setString(2, subTodo.getDescription());
            preparedStatement.setString(3, subTodo.getStatus());
            preparedStatement.setInt(5,subTodo.getParentId());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }


    public static void completeUpdateSubTodos(String pId) throws ClassNotFoundException{

        String sql = "update sub_todos "
                + "set status=? "
                + "where parent_id=?";
        int parentId = Integer.parseInt(pId);
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "Completed");
            preparedStatement.setInt(2,parentId);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
    }

    public static void deleteAllSubTodoById(String todoID) throws ClassNotFoundException{

        String sql = "delete from sub_todos where parent_id=?";
        int parentId = Integer.parseInt(todoID);

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, parentId);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

    }

    public static void deleteSubTodo(String todoID,int parentId) throws ClassNotFoundException{

        String sql = "delete from sub_todos where id=? and parent_id=?";
        int id = Integer.parseInt(todoID);

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2,parentId);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }

    }

    public static SubTodo getSubTodo(String subtodoID, int parentId) throws ClassNotFoundException{

        String sql = "select * from sub_todos where id=? and parent_id=?";
        SubTodo subTodo = null;
        int id = Integer.parseInt(subtodoID);
        ResultSet myRs = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2,parentId);


            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            myRs = preparedStatement.executeQuery();
            if (myRs.next()) {
                String subTopic = myRs.getString("sub_topic");
                String description = myRs.getString("description");
                String status = myRs.getString("status");

                // use the todoId during construction
                subTodo = new SubTodo (id, subTopic, description, status ,parentId);
            }
            else {
                throw new Exception("Could not find todo id: " + subtodoID);
            }

            return subTodo;

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subTodo;
    }

    public static List<SubTodo> getSubTodos(int parentId) throws ClassNotFoundException{

        String sql = "select * from sub_todos where parent_id=?";
        ResultSet myRs = null;
        List<SubTodo> subTodos = new ArrayList<>();
        SubTodo subTodo = null;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,parentId);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            myRs = preparedStatement.executeQuery();
            while (myRs.next()) {
                // retrieve data from result set row
                int id = myRs.getInt("id");
                String subTopic = myRs.getString("sub_topic");
                String description = myRs.getString("description");
                String status = myRs.getString("status");

                // create new todo object
                subTodo = new SubTodo(id, subTopic, description, status,parentId);

                // add it to the list of todos
                subTodos.add(subTodo);
            }
            return subTodos;

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return subTodos;
    }

    public static void addSubTodo(SubTodo subTodo) throws ClassNotFoundException {
        String sql = "insert into sub_todos "
                + "(sub_topic, description, status , parent_id) "
                + "values (?, ?, ?,?)";

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/employee_management_system?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, subTodo.getSubTopic());
            preparedStatement.setString(2, subTodo.getDescription());
            preparedStatement.setString(3, subTodo.getStatus());
            preparedStatement.setInt(4,subTodo.getParentId());

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
