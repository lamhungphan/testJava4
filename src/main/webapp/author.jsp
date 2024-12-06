<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Author Management</title>
</head>
<body>
<div>
    <h2>Author Form</h2><br>
    <h4 style="color: red">${message}</h4>
    <form action="${pageContext.request.contextPath}/author" method="post" style="margin-bottom: 20px;">
        <label>Id:</label>
        <input type="text" name="id" value="${editAuthor != null ? editAuthor.id : ''}" required>
        <br>
        <label>Name:</label>
        <input type="text" name="name" value="${editAuthor != null ? editAuthor.name : ''}" required>
        <br>
        <button type="submit" formaction="${pageContext.request.contextPath}/author?action=create">Create</button>
        <button type="submit" formaction="${pageContext.request.contextPath}/author?action=delete"
                onclick="return confirm('Are you sure you want to delete this author?');">Delete
        </button>

        <c:if test="${editAuthor != null}">
            <button>
                <a href="${pageContext.request.contextPath}/author" style="margin-left: 10px;">Cancel</a>
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
        <c:forEach var="author" items="${authors}" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/author" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="id" value="${author.id}">
                        <button type="submit">Edit</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/book" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="show_book">
                        <input type="hidden" name="id" value="${author.id}">
                        <button type="submit">Show book</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
