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
        <h1>Kyra and Jelle's wild bookcase mania!</h1>
        <p style="text-align: center; text-decoration: underline">We don't care about front pages, we care about books!</p><br>

        <p>
            <c:if test="${!empty user}">
                Hello ${user}!
            </c:if>

            <c:if test="${empty user}">
                Welcome guest, search for existing bookcases or <a href="<c:url value="/signup"/>">create an account</a> and share your own bookcases!
            </c:if>
        </p>

    </article>



</body>
</html>
