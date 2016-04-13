<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 13-4-2016
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BookCase</title>
    <jsp:include page="LoginBar.jsp" />
</head>
<body>
<p>Deze user bestaat niet!</p>
<a href="<c:url value="/index"/>">Terug naar hoofdpagina</a>
</body>
</html>
