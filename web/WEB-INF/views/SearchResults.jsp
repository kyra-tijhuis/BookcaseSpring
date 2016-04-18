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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script type='text/javascript' src='resources/jquery.mousewheel.js'></script>
    <script type='text/javascript' src='resources/scrolllist.js'></script>

    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/searchresults.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/scrolllist.css" />/>
    <jsp:include page="LoginBar.jsp" />
</head>
<body>



    <article id="mainscreen">
        <h1>Search bookcases</h1>
        <c:url value="/search" var="x"/>
        <form:form id="mainsearch" action="${x}" method="post" modelAttribute="SearchForm">
            <form:label class="searchlabel" path="bookcaseName">Bookcase:</form:label>
            <form:input class="searchinput" path="bookcaseName" type="text" />
            <form:label class="searchlabel" path="username">User:</form:label>
            <form:input class="searchinput" path="username" type="text"/>
            <form:label class="searchlabel" path="bookName">Book:</form:label>
            <form:input class="searchinput" path="bookName" type="text"/>
            <form:button id="mainsearchbutton" type="submit">Search</form:button>
        </form:form>

        <p id="mainparagraph">
            <strong>Search results for:</strong><br><br>
            <table>
                <tr><td class="searchtable">Bookcase name:</td><td>${param.bookcaseName}</td></tr>
                <tr><td class="searchtable">User name:</td><td>${param.username}</td></tr>
                <tr><td class="searchtable">Book name:</td><td>${param.bookName}</td></tr>
            </table>

        </p><br>

        <ul id="buttonlist">
            <li><img class="buttonup" src="resources/arrow.png"/></li>
            <li><br><br><br></li>
            <li><img class="buttondown" src="resources/arrow.png"/></li>
        </ul>
        <ul class="scrolllist">
            <c:forEach var="item" items="${searchlist}">
                <li class="scrollitem"><table class="contenttable"><tr><td><img src="resources/bookcase.png"/></td><td><h5>${item.getName()}</h5><h6>user: Kyra</h6></td></tr></table></li>
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