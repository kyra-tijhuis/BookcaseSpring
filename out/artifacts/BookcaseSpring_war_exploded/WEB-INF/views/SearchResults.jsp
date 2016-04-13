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
    <jsp:include page="LoginBar.jsp" />
</head>
<body>
    <p>Zoekresultaten voor de term ${query}</p>
    <a href="<c:url value="/index"/>">Terug naar hoofdpagina</a>
</body>
</html>