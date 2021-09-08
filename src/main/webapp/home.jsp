<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dhnam
  Date: 9/8/2021
  Time: 11:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<form action="products?action=create" menthod="post"></form>--%>
<table class="table table-striped">
    <tr>
        <th>#</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Color</th>
        <th>Category</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${products}" varStatus="loop" var="p">
        <tr>
            <td>${loop.index}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td>${p.quantity}</td>
            <td>${p.color}</td>
            <td>${p.category}</td>
        </tr>
    </c:forEach>
</table>
<a href="/products?action=create"><button>Thêm</button></a>
</body>
</html>
