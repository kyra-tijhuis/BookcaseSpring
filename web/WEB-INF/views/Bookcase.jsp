<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 19-4-2016
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BookCase</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/bookcase.css" />/>

    <jsp:include page="LoginBar.jsp" />
</head>
<body>

<article id="mainscreen">
    ${param.id}
    <div id="bookcase">
        <div id="bookcasetop"></div>
        <div id="bookcaseleft"></div>
        <div id="bookcaseright"></div>
        <div id="bookcasebottom"></div>
    </div>

    <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>
</article>
</body>
</html>

