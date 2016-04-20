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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script type='text/javascript' src='../resources/jquery.mousewheel.js'></script>
    <script type='text/javascript' src='../resources/scrolllist.js'></script>

    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/scrolllist.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/user.css" />/>

    <jsp:include page="LoginBar.jsp" />
</head>
<body>

    <article id="mainscreen">
        <h1>${userName}'s page</h1>


        <ul id="buttonlist">
            <li><img class="buttonup" src="../resources/arrow.png"/></li>
            <li><br><br><br></li>
            <li><img class="buttondown" src="../resources/arrow.png"/></li>
        </ul>
        <ul class="scrolllist">
            <c:forEach var="item" items="${searchlist}">
                <c:url value="/bookcase?id=${item.getBookcaseID()}" var="x"/>
                <li class="scrollitem"><a href="${x}"><table class="contenttable"><tr><td><img src="../resources/bookcase.png"/></td><td><h5>${item.getBookcaseName()}</h5><h6>user: Kyra</h6></td></tr></table></a></li>
            </c:forEach>
        </ul>
        <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>

        <script>
            $(document).ready(
                    makelist
            );
        </script>
    </article>
</body>
</html>
