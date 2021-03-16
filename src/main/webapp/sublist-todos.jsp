<%--
  Created by IntelliJ IDEA.
  User: Mohanraj G
  Date: 15-03-2021
  Time: 12:19
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
        <h2> Group : "${mainTopic}"  </h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <input type="button" value="Add Todo"
               onclick="window.location.href='add-todo-subform.jsp'; return false;"
               class="add-todo-button"
        />

        <table>

            <tr>
                <th>Sub Topic</th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="temp" items="${TODOSUB_LIST}">

                <!-- set up a link for each todo -->
                <c:url var="tempLink" value="SubServlet">
                    <c:param name="command" value="LOAD" />
                    <c:param name="subtodoID" value="${temp.id}" />
                </c:url>

                <!--  set up a link to delete a todo -->
                <c:url var="deleteLink" value="SubServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="subtodoID" value="${temp.id}" />
                </c:url>

                <!-- set up a link to mark complete -->
                <c:url var="completeLink" value="SubServlet">
                    <c:param name="command" value="COMPLETE" />
                    <c:param name="subtodoID" value="${temp.id}" />
                </c:url>


                <tr>
                    <td> ${temp.subTopic} </td>
                    <td> ${temp.description} </td>
                    <td> ${temp.status} </td>
                    <td>
                        <a href="${tempLink}">Update</a>
                        |
                        <a href="${deleteLink}"
                           onclick="if (!(confirm('Are you sure you want to delete this todo?'))) return false">
                            Delete</a>
                        |
                        <a href="${completeLink}">Completed</a>
                    </td>
                </tr>

            </c:forEach>

        </table>

    </div>
    <div style="clear: both;"></div>

    <p>
        <a href="ToDoServlet">Back to List</a>
    </p>
</div>


</body>


</html>








