<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>BookCase</title>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <jsp:include page="LoginBar.jsp" />
</head>
<body>
    <article id="mainscreen">
        <p>
            <c:url value="/signup" var="x"/>
            <form:form action="${x}" method="post" modelAttribute="SignupForm">
                <form:input path="username" type="text" />  <br>
                <form:errors path="username" cssClass="error" /><br>
                <form:password path="password"/><br>
                <form:errors path="password" cssClass="error" /><br>
                <form:password path="password2"/><br>
                <form:errors path="password2" cssClass="error" /><br>
                <form:button type="submit">Sign Up!</form:button>
            </form:form>


        </p>
    </article>
</body>
</html>