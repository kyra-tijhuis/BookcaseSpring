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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/bookcase.css" />/>

    <jsp:include page="LoginBar.jsp" />
    <style>
        body { font-size: 62.5%; }
        label, input { display:block; }
        input.text { margin-bottom:12px; width:95%; padding: .4em; }
        fieldset { padding:0; border:0; margin-top:25px; }
        h1 { font-size: 1.2em; margin: .6em 0; }
        div#users-contain { width: 350px; margin: 20px 0; }
        div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
        div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
        .ui-dialog .ui-state-error { padding: .3em; }
        .validateTips { border: 1px solid transparent; padding: 0.3em; }
    </style>

</head>
<body>

<div id="dialog-form" title="Add book">
    <p class="validateTips">All form fields are required.</p>

    <form>
        <fieldset>
            <label for="isbn">ISBN</label>
            <input type="text" name="isbn" id="isbn" placeholder="1234567890123" class="text ui-widget-content ui-corner-all">
            <label for="title">Title</label>
            <input type="text" name="title" id="title" placeholder="Book title" class="text ui-widget-content ui-corner-all">
            <label for="author">Author</label>
            <input type="text" name="author" id="author" placeholder="A. Author" class="text ui-widget-content ui-corner-all">

            <!-- Allow form submission with keyboard without duplicating the dialog button -->
            <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
        </fieldset>
    </form>
</div>



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
    <td id="bookcaseright"></td>
    </tr></table>

    <table id="buttontable">
        <ul id="buttonlist">
            <c:forEach var="plank" items="${bookcase.planks}">
                <li class="buttons"  style="height: ${plank.height + 15}">
                    <button class="addbutton" data-plankid="${plank.plankID}">Add book to this plank</button>
                </li>
            </c:forEach>
        </ul>
    </table>

    <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>



    <script>
        $(function() {
            var ISBN, Title, Author, plankid;

            var dialog, form,

            // From http://www.whatwg.org/specs/web-apps/current-work/multipage/states-of-the-type-attribute.html#e-mail-state-%28type=email%29
                    titleRegex = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+$/,
                    isbn = $( "#isbn" ),
                    title = $( "#title" ),
                    author = $( "#author" ),
                    allFields = $( [] ).add( isbn ).add( title ).add( author ),
                    tips = $( ".validateTips" );

            function updateTips( t ) {
                tips
                        .text( t )
                        .addClass( "ui-state-highlight" );
                setTimeout(function() {
                    tips.removeClass( "ui-state-highlight", 1500 );
                }, 500 );
            }

            function checkLength( o, n, min, max ) {
                if ( o.val().length > max || o.val().length < min ) {
                    o.addClass( "ui-state-error" );
                    updateTips( "Length of " + n + " must be between " +
                            min + " and " + max + "." );
                    return false;
                } else {
                    return true;
                }
            }

            function checkRegexp( o, regexp, n ) {
                if ( !( regexp.test( o.val() ) ) ) {
                    o.addClass( "ui-state-error" );
                    updateTips( n );
                    return false;
                } else {
                    return true;
                }
            }

            function addBook() {
                var valid = true;
                allFields.removeClass( "ui-state-error" );

                valid = valid && checkLength( isbn, "ISBN", 10, 13 );
                valid = valid && checkLength( title, "title", 1, 100 );
                valid = valid && checkLength( author, "author", 1, 100 );

                valid = valid && checkRegexp( isbn, /^([0-9])+$/i, "ISBN must consist of 10 or 13 numbers." );
                valid = valid && checkRegexp( title, titleRegex, "The title of the book" );
                valid = valid && checkRegexp( author, /^([0-9a-zA-Z.,&+/?'-])+$/, "The author of the book" );

                if ( valid ) {
                    ISBN = isbn.val();
                    Title = title.val();
                    Author = author.val();
                    dialog.dialog( "close" );
                }
                return valid;
            }

            dialog = $( "#dialog-form" ).dialog({
                autoOpen: false,
                height: 300,
                width: 350,
                modal: true,
                buttons: {
                    "Add a book": addBook,
                    Cancel: function() {
                        dialog.dialog( "close" );
                    }
                },
                close: function() {
                    form[ 0 ].reset();
                    allFields.removeClass( "ui-state-error" );


                    <c:url value="/addbook" var="targeturl"/>
                    $.get("${targeturl}", {username:"${userName}", plankID: plankid, ISBN: ISBN, Title: Title, Author: Author}, function(data) {
                        console.log(data[0]);

                        var width = data[0];
                        var bookheight = data[1];
                        var plankheight = data[2];
                        var imageurl = data[3];

                        var string = '<li class="book" style="width:'+ width +'"><img style="top: ' + (plankheight - bookheight) + '; height: ' + bookheight +'" src="' + imageurl +'"/></li>'
                        console.log(string);

                        var jquerystring = '#' + plankid + ' .booklist';
                        console.log(jquerystring);

                        $(jquerystring).append(string);



                    })



                }
            });

            form = dialog.find( "form" ).on( "submit", function( event ) {
                event.preventDefault();
                addBook();
            });

            $('.addbutton').click(function() {
                plankid = $(this).data("plankid");

                dialog.dialog( "open" );

            })
        });





        $(document).ready(function() {
            $('#addplankbutton').click(function() {
                <c:url value="/addplank" var="targeturl"/>
                $.get("${targeturl}", {username:"${userName}", bookcaseID: "${bookcase.getBookcaseID()}"}, function(data) {
                    var string = '<li class="books" id="'+data+'" style="height: 300"><ul class="booklist"></ul></li><li class="plank"></li>'
                    $('#planks').append(string);
                })
            })




        }



        );




    </script>





</article>
</body>
</html>

