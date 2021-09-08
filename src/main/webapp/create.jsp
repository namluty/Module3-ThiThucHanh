<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dhnam
  Date: 9/8/2021
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="format-detection" content="telephone=no" />
</head>
<body>
<h1>Tao san pham</h1>
<form action="products" menthod ="post">

</form>
<input type="text" name="name">
<input type="number" name="price">
<input type="text" name="quantity">
<input type="text" name="color">
<input type="text" name="description">
<select name="categories">
    <c:forEach items="${categories}" var="c">
        <option value="${c.id}">${c.name}</option> </option>
    </c:forEach>
</select>
<button type="submit">Thêm</button>
</body>
</html>
