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
               class="add-todo-button"
        />

        <table>

            <tr>
                <th>Main Topic</th>
                <th> Type </th>
                <th>Description</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="temp" items="${TODO_LIST}">

                <!-- set up a link for each todo -->
                <c:url var="tempLink" value="ToDoServlet">
                    <c:param name="command" value="LOAD" />
                    <c:param name="todoID" value="${temp.id}" />
                </c:url>

                <!--  set up a link to delete a todo -->
                <c:url var="deleteLink" value="ToDoServlet">
                    <c:param name="command" value="DELETE" />
                    <c:param name="todoID" value="${temp.id}" />
                </c:url>

                <!-- set up a link to mark complete -->
                <c:url var="completeLink" value="ToDoServlet">
                    <c:param name="command" value="COMPLETE" />
                    <c:param name="todoID" value="${temp.id}" />
                </c:url>

                <!-- set up a link to sub task -->
                <c:url var="subLink" value="SubServlet">
                    <c:param name="todoParentID" value="${temp.id}" />
                    <c:param name="mainTopic" value="${temp.topic}" />
                </c:url>

                <tr>
                    <td>
                        <c:if test="${temp.type == 'group'}">
                            <a href="${subLink}">${temp.topic}</a>
                        </c:if>
                        <c:if test="${temp.type != 'group'}">
                            <a>${temp.topic}</a>
                        </c:if>
                    </td>
                    <td > ${temp.type} </td>
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

</div>
</body>


</html>








