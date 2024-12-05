<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<h2>Product Form</h2><br>
<select>
    <option value="all">All</option>
    <c:forEach var="category" items="${categories}">
        <option value="${category.id}">${category.name}</option>
    </c:forEach>
</select>
<button>Show</button>
<br>
<!-- Khi Create hay Delete bên trang category.jsp bên trang Products phải thay đổi theo -->
<table border="1">
    <thead>
    <tr></tr>
    <th>Id</th>
    <th>Name</th>
    <th>Price</th>
    <th>Category Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.category.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
