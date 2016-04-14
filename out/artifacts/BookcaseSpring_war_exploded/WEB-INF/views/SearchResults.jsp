<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 13-4-2016
  Time: 12:16
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
    <jsp:include page="LoginBar.jsp" />
</head>
<body>
    <article id="mainscreen">
        <p> Zoekresultaten voor de term ${param.query}

            <a href="<c:url value="/index"/>">Terug naar hoofdpagina</a>

            <a href="<c:url value="/search"/>">Extended search</a>
        </p>
    </article>
</body>
</html>