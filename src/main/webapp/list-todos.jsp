<%--
  Created by IntelliJ IDEA.
  User: Mohanraj G
  Date: 15-03-2021
  Time: 07:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
    <title>ToDo App</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2>TODO APPLICATION</h2>
    </div>
</div>

<div id="container">

    <div id="content">


        <input type="button" value="Add Todo"
               onclick="window.location.href='add-todo-form.jsp'; return false;"
               class="add-student-button"
        />

        <table>

            <tr>
                <th>Topic</th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="temp" items="${TODO_LIST}">

                <!-- set up a link for each student -->
                <c:url var="tempLink" value="ToDoServlet">
                    <c:param name="command" value="LOAD" />
                    <c:param name="todoID" value="${temp.id}" />
                </c:url>

                <!--  set up a link to delete a student -->
                <c:url var="deleteLink" value="ToDoServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="todoID" value="${temp.id}" />
                </c:url>

                <tr>
                    <td> ${temp.topic} </td>
                    <td> ${temp.description} </td>
                    <td> ${temp.status} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this todo?'))) return false">
                            Delete</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>

</div>
</body>


</html>








