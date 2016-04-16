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

    <script type='text/javascript' src='http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js'></script>


    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/searchresults.css" />/>
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

        <p id="mainparagraph">Zoekresultaten voor de term ${param.bookcaseName}<br>

            <a href="<c:url value="/index"/>">Terug naar hoofdpagina</a>
        </p><br><br><br>
        <ul id="testlist">
            <li><table class="contenttable"><tr><td><img src="resources/bookcase.png"/></td><td><h5>Mooie boeken</h5><h6>user: Kyra</h6></td></tr></table></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
            <li><img src="resources/bookcase.png"/></li>
        </ul>






        
    </article>


</body>
</html>