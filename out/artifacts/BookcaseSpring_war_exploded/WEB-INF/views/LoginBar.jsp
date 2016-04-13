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
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/bar.css"/> <%--TODO make link working--%>
</head>
<body>
    <article id="header">
        <c:if test="${empty user}">
            <c:url value="/login" var="x"/>
            <form:form class="form" action="${x}"  method="post" modelAttribute="LoginForm">

                <form:input path="username" placeholder = "${unPlaceholder}"/>

                <form:password path="password" placeholder = "${pwPlaceholder}"/>

                <form:input path="url" type="hidden" value="${address}" />
                <button type="submit">Login</button>
            </form:form>
        </c:if>

        <c:if test="${!empty user}">
            <c:url value="/logout" var="y"/>
            <c:url value="/user/" var="z"/>
            <a id="loggedUser" href="${z}${user}">${user}</a>
            <form:form id="logoutForm" class="form" action="${y}" method="post">
                <button type="submit", name="url", value="${address}">Logout</button>
            </form:form>
        </c:if>
    </article>
</body>
</html>
