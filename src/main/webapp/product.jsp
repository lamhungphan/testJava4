<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
</head>
<body>
<h2>Product Form</h2><br>
<h4 style="color: red">${message}</h4>
<form action="${pageContext.request.contextPath}/product" method="POST">
    <select name="category_id">
        <option value="all"
                <c:if test="${empty param.category_id or param.category_id == 'all'}">selected</c:if>> All
        </option>
        <c:forEach var="category" items="${categories}">
            <option value="${category.id}"
                    <c:if test="${category.id == param.category_id}">selected</c:if>> ${category.name}
            </option>
        </c:forEach>
    </select>
    <button type="submit" name="action" value="filter">Show</button>
</form>
<br>
<table border="1">
    <thead>
    <tr>
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
