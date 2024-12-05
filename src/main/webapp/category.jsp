<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category</title>
</head>
<body>
<div>
    <h2>Category Form</h2><br>
    <form action="/category" method="post">
        <input type="hidden" name="action" value="create">
        <label for="name">Name:</label>
        <input type="text" name="name" id="name">
        <button type="submit">Create</button>
    </form>
    <hr>
    <!-- Không cho xóa Category đã có sản phẩm ( Product ), hiện thông báo Loại hàng này đã có sản phẩm -->

    <table>
        <thead>
        <tr>
            <th>No</th>
            <th>Id</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>
                    <form action="/category" method="post" style="display: inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${category.id}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
