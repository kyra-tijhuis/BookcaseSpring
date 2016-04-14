<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 11-4-2016
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>BookCase</title>

    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/mainscreen.css" />/>
    <jsp:include page="LoginBar.jsp" />


</head>
<body>
    <article id="mainscreen">
        <h1>Kyra en Jelle's wilde boekenkasten!</h1>
        <form:form id="form" action="/search" method="get" modelAttribute="SearchForm">
            <form:input path="bookcaseName" placeholder="find bookcase name..."/>
            <form:button type="submit">Search</form:button>
        </form:form>

        <p>hallo, ik ben een pagina!</p>
    </article>

</body>
</html>
