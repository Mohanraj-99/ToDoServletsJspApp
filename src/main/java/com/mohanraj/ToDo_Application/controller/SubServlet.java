package com.mohanraj.ToDo_Application.controller;

import com.mohanraj.ToDo_Application.dao.SubTodoDao;
import com.mohanraj.ToDo_Application.model.SubTodo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.List;

@WebServlet("/SubServlet")
public class SubServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        try {
        // read the "command" parameter
        String theCommand = request.getParameter("command");
        System.out.println(theCommand);
        String parentId = request.getParameter("todoParentID");
        String mainTopic = request.getParameter("mainTopic");
        if(parentId!= null) {
            HttpSession session = request.getSession();
            session.setAttribute("parentId",parentId);
            session.setAttribute("mainTopic",mainTopic );
        }

            // if the command is missing, then default to listing todos
        if (theCommand == null) {
            theCommand = "LIST";
        }

        // route to the appropriate method
        switch (theCommand) {

            case "ADD":
                addSubTodo(request, response);
                break;

            case "LOAD":
                loadSubTodo(request, response);
                break;

            case "UPDATE":
                updateSubTodo(request, response);
                break;

            case "DELETE":
                deleteSubTodo(request, response);
                break;

            case "COMPLETE":
                setSubCompleteStatus(request,response);
                break;

            default:
                listSubTodos(request, response);
        }

    } catch (Exception exc) {
        throw new ServletException(exc);
    }

}

    private void setSubCompleteStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception  {
        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);

        // onClick - to status change
        SubTodo subTodo = SubTodoDao.getSubTodo(request.getParameter("subtodoID"),parentId);
        subTodo.setStatus("Completed");

        // perform update on database
        SubTodoDao.updateSubTodo(subTodo);

        // send them back to the "list todos" page
        listSubTodos(request, response);

    }

    private void deleteSubTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read Subtodo id from form data
        String subtodoID = request.getParameter("subtodoID");
        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);

        // delete todo from database
        SubTodoDao.deleteSubTodo(subtodoID,parentId);

        // send them back to "list todo" page
        listSubTodos(request, response);
    }

    private void updateSubTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read todo info from form data
        int id = Integer.parseInt(request.getParameter("id"));
        String subtopic = request.getParameter("subtopic");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);


        // create a new todo object
        SubTodo subTodo = new SubTodo(id, subtopic, description, status , parentId);

        // perform update on database
        SubTodoDao.updateSubTodo(subTodo);

        // send them back to the "list todos" page
        listSubTodos(request, response);

    }

    private void loadSubTodo(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // read todo id from form data
        String id = request.getParameter("subtodoID");
        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);


        // get todo from database (db util)
        SubTodo subTodo = SubTodoDao.getSubTodo(id,parentId);

        // place todo in the request attribute
        request.setAttribute("theSubTodo", subTodo);

        // send to jsp page: update-subtodo-form.jsp
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/update-subtodo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void addSubTodo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // read todo info from form data
        String subtopic = request.getParameter("subtopic");
        String description = request.getParameter("description");
        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);

        // create a new todo object
        SubTodo subTodo = new SubTodo(subtopic, description, "Incomplete", parentId);

        // add the todo to the database
        SubTodoDao.addSubTodo(subTodo);

        // send back to main page (the todo list)
        listSubTodos(request, response);
    }

    private void listSubTodos(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session=request.getSession(false);
        String parentId1 =(String)session.getAttribute("parentId");
        int parentId = Integer.parseInt(parentId1);

        // get todos from db util
        List<SubTodo> subTodos = SubTodoDao.getSubTodos(parentId);

        // add todo to the request
        request.setAttribute("TODOSUB_LIST", subTodos);

        // send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sublist-todos.jsp");
        dispatcher.forward(request, response);
    }
}

