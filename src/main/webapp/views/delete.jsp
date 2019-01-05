<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 05/01/2019
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting product</title>
</head>
<body>
<h1>Delete product</h1>
<p>
    <a href="<c:url value="/products"/>">Back to product list</a>
</p>
<form method="post">
    <h3>Are you sure?</h3>
    <fieldset>
        <legend>Customer information</legend>
        <table>
            <tr>
                <td>ID: </td>
                <td>${requestScope["product"].id}</td>
            </tr>
            <tr>
                <td>Name: </td>
                <td>${requestScope["product"].name}</td>
            </tr>
            <tr>
                <td>Price: </td>
                <td>${requestScope["product"].price}</td>
            </tr>
            <tr>
                <td><input type="submit" value="Delete product"></td>
                <td><a href="<c:url value="/products"/>">Back to product list</a></td>
            </tr>
        </table>
    </fieldset>
</body>
</html>

