<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 11-4-2016
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<html>
<head>
    <title></title>
    <link rel="stylesheet" href="bar.css"> <%--TODO make link working--%>
</head>
<body>
    <article>
        ${user}
        <c:if test="${empty user}">
            <form:form action="login" method="post" modelAttribute="LoginForm">
                <form:label path="username">
                    Username <form:errors path="username" cssClass="error" />
                </form:label>
                <form:input path="username" />

                <form:label path="password">
                    Password <form:errors path="password" cssClass="error" />
                </form:label>
                <form:password path="password" />

                <form:input path="url" type="hidden" value="${address}" />

                <button type="submit">Login</button>
            </form:form>
        </c:if>

        <c:if test="${!empty user}">
            <a href="user/${user}">${user}</a>
            <form:form action="logout" method="post">
                <button type="submit", name="url", value="${address}">Logout</button>
            </form:form>
        </c:if>




    </article>


</body>
</html>
