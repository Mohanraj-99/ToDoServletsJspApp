<%--
  Created by IntelliJ IDEA.
  User: Mohanraj G
  Date: 15-03-2021
  Time: 07:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Add Todo</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-todo-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>TODO APPLICATION</h2>
    </div>
</div>

<div id="container">
    <h3>Add ToDo</h3>

    <form action="ToDoServlet" method="GET">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Topic:</label></td>
                <td>
                    <label>
                        <input type="text" name="topic" />
                    </label>
                </td>
            </tr>

            <tr>
                <td><label>Description:</label></td>
                <td>
                    <label>
                        <input type="text" name="description" />
                    </label>
                </td>
            </tr>

            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form>

    <div style="clear: both;"></div>

    <p>
        <a href="ToDoServlet">Back to List</a>
    </p>
</div>
</body>

</html>











