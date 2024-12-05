<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category Management</title>
</head>
<body>
<div>
    <h2>Category Form</h2><br>
    <h4 style="color: red">${message}</h4>
    <form action="${pageContext.request.contextPath}/category" method="post" style="margin-bottom: 20px;">
        <label>Id:</label>
        <input type="text" name="id" value="${editCategory != null ? editCategory.id : ''}" required>
        <br>
        <label>Name:</label>
        <input type="text" name="name" value="${editCategory != null ? editCategory.name : ''}" required>
        <br>
        <button type="submit" formaction="${pageContext.request.contextPath}/category?action=create">Create</button>
        <button type="submit" formaction="${pageContext.request.contextPath}/category?action=delete"
                onclick="return confirm('Are you sure you want to delete this category?');">Delete
        </button>

        <c:if test="${editCategory != null}">
            <button>
                <a href="${pageContext.request.contextPath}/category" style="margin-left: 10px;">Cancel</a>
            </button>
        </c:if>
    </form>
    <hr>
    <table border="1" cellspacing="0" cellpadding="5">
        <thead>
        <tr>
            <th>No</th>
            <th>Id</th>
            <th>Name</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${category.id}</td>
                <td>${category.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/category" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="${category.id}">
                        <button type="submit">Edit</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
