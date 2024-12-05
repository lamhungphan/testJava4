<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Statistics</title>
</head>
<body>
<h2>Product Statistics</h2><br>
<table border="1">
    <thead>
    <tr>
        <th>Category</th>
        <th>Number of Product</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="stat" items="${statistics}">
        <tr>
            <td>${stat.categoryName}</td>
            <td>${stat.productCount}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
