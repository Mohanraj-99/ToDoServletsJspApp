<%--
  Created by IntelliJ IDEA.
  User: Mohanraj G
  Date: 15-03-2021
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Add Sub-Todo</title>

    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/add-todo-style.css">
</head>

<body>
<div id="wrapper">
    <div id="header">
        <h2>SUB-TODO APPLICATION</h2>
    </div>
</div>

<div id="container">
    <h3>Add Sub-ToDo</h3>

    <form action="SubServlet" method="GET">

        <input type="hidden" name="command" value="ADD" />

        <table>
            <tbody>
            <tr>
                <td><label>Sub Topic:</label></td>
                <td>
                    <label>
                        <input type="text" name="subtopic" />
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
        <a href="SubServlet">Back to List</a>
    </p>
</div>
</body>

</html>











