package com.mohanraj.ToDo_Application.controller;

import com.mohanraj.ToDo_Application.dao.TodoDao;
import com.mohanraj.ToDo_Application.model.Todo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ToDoServlet")
public class ToDoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // read the "command" parameter
            String theCommand = request.getParameter("command");
            System.out.println(theCommand);

            // if the command is missing, then default to listing todos
            if (theCommand == null) {
                theCommand = "LIST";
            }

            // route to the appropriate method
            switch (theCommand) {

                case "ADD":
                    addTodo(request, response);
                    break;

                case "LOAD":
                    loadTodo(request, response);
                    break;

                case "UPDATE":
                    updateTodo(request, response);
                    break;

                case "DELETE":
                    deleteTodo(request, response);
                    break;
                case "COMPLETE":
                    setCompleteStatus(request,response);
                    break;

                default:
                    listTodos(request, response);
            }

        } catch (Exception exc) {
            throw new ServletException(exc);
        }

    }

    private void setCompleteStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        // onClick - to status change
        Todo todo = TodoDao.getTodo(request.getParameter("todoID"));
        todo.setStatus("Completed");

        // perform update on database
        TodoDao.updateTodo(todo);

        // send them back to the "list todos" page
        listTodos(request, response);

    }

    private void deleteTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read todo id from form data
        String todoID = request.getParameter("todoID");

        // delete todo from database
        TodoDao.deleteTodo(todoID);

        // send them back to "list todo" page
        listTodos(request, response);
    }

    private void updateTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read todo info from form data
        int id = Integer.parseInt(request.getParameter("id"));
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");
        String status = request.getParameter("status");


        // create a new todo object
        Todo todo = new Todo(id, topic, description, status);

        // perform update on database
        TodoDao.updateTodo(todo);

        // send them back to the "list todos" page
        listTodos(request, response);

    }

    private void loadTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read todo id from form data
        String id = request.getParameter("todoID");

        // get todo from database (db util)
        Todo todo = TodoDao.getTodo(id);

        // place todo in the request attribute
        request.setAttribute("theTodo", todo);

        // send to jsp page: update-todo-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-todo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addTodo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read todo info from form data
        String topic = request.getParameter("topic");
        String description = request.getParameter("description");

        // create a new todo object
        Todo todo = new Todo(topic, description, "Incomplete");

        // add the todo to the database
        TodoDao.addTodo(todo);

        // send back to main page (the todo list)
        listTodos(request, response);
    }

    private void listTodos(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // get todos from db util
        List<Todo> todos = TodoDao.getTodos();

        // add todo to the request
        request.setAttribute("TODO_LIST", todos);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-todos.jsp");
        dispatcher.forward(request, response);
    }
}
