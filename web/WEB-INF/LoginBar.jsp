<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 11-4-2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

%>
<html>
<head>
    <title></title>
</head>
<body>
    <c:if test="${empty username}">
        <form action="/Login" method="post">
            <label>Username:</label><input type="text" name="username">
            <label>Password:</label><input type="password" name="password">
            <input type="hidden" name="url" value="${address}">
            <input type="submit">
        </form>
    </c:if>
    <c:if test="${!empty username}">
        <a href="/user">${username}
        </a><form method="post" action="/index">
            <button name="submit" value="submit" type="submit">Logout</button>
        </form>
    </c:if>


</body>
</html>
