<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h4><%= "PS39152 - Nguyễn Phan Lâm Hùng" %></h4>
<br/>
<%--<a href="${pageContext.request.contextPath}/category">Category</a>--%>
<%--<a href="${pageContext.request.contextPath}/product">Product</a>--%>
<%--<a href="${pageContext.request.contextPath}/statistic">Statistics</a>--%>
<a href="${pageContext.request.contextPath}/author">Author</a>
<a href="${pageContext.request.contextPath}/book">Book</a>
<hr>
<jsp:include page="${view}"/>
</body>
</html>
