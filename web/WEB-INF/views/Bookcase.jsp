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
    <h1>${bookcase.bookcaseName}</h1>
    <h4>A bookcase by ${userName}</h4>

    <c:if test="${user == userName}">
        <button id="addplankbutton">Add plank!</button>
    </c:if>

    <table id="bookcase" style="width: ${bookcase.width}; height: ${bookcaseheight}"><tr><td id="bookcaseleft"></td>
        <td id="plankcell"><ul id="planks">
            <li id="topplank"></li>
            <c:forEach var="plank" items="${bookcase.planks}">
                <li class="books" id="${plank.plankID}" style="height: ${plank.height}">
                    <ul class="booklist">
                        <c:forEach var="book" items="${plank.books}">
                            <li class="book" style="width: ${book.book.width}"><img style="top: ${plank.height - book.book.height}; height: ${book.book.height} " src="${goodreadsDAO.getImage(book.book.isbn)}""/></li>
                        </c:forEach>
                    </ul>
                </li>
                <li class="plank"></li>
            </c:forEach>

        </ul></td>
    <td id="bookcaseright"></td></tr></table>

    <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>


    <script>
        $(document).ready(
            $('#addplankbutton').click(function() {
                <c:url value="/addplank" var="targeturl"/>
                $.get("${targeturl}", {username:"${userName}", bookcaseID: "${bookcase.getBookcaseID()}"}, function(data) {
                        var string = '<li class="books" id="'+data+'" style="height: 300"><ul class="booklist"></ul></li><li class="plank"></li>'
                        $('#planks').append(string);


                })
            })
        );
    </script>





</article>
</body>
</html>

