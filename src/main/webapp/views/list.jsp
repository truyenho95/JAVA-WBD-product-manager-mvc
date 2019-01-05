<%--
  Created by IntelliJ IDEA.
  User: justin
  Date: 05/01/2019
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customers</h1>
<p>
    <a href="<c:url value="/products?action=create"/>">Create new product</a>
</p>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["products"]}' var="product">
        <tr>
            <td><a href="<c:url value="/products?action=view&id=${product.id}"/>">${product.id}</a></td>
            <td><a href="<c:url value="/products?action=view&id=${product.id}"/>">${product.name}</a></td>
            <td>${product.price}</td>
            <td><a href="<c:url value="/products?action=edit&id=${product.id}"/>">edit</a></td>
            <td><a href="<c:url value="/products?action=delete&id=${product.id}"/>">delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
