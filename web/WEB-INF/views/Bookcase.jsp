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
    ${bookcase.bookcaseName}

    <table id="bookcase" style="width: ${bookcase.width}; height: ${bookcaseheight}"><tr><td id="bookcaseleft"></td>
        <td id="plankcell"><ul id="planks">
            <li id="topplank"></li>
            <c:forEach var="plank" items="${bookcase.planks}">
            <li class="books" style="height: ${plank.height}">
                <ul class="booklist">
                    <c:forEach var="book" items="${plank.books}">
                        <li class="book" style="width: ${book.book.width}"><img style="top: ${plank.height - book.book.height}; height: ${book.book.height} " src="http://d.gr-assets.com/books/1387708305l/6300.jpg"/></li>
                    </c:forEach>
                </ul>
            </li>
            <li class="plank"></li>
            </c:forEach>
        </ul></td>
    <td id="bookcaseright"></td></tr></table>

    <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>
</article>
</body>
</html>

