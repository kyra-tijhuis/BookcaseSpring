<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>BookCase</title>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/general.css" />/>
    <link rel="stylesheet" type="text/css" href=<c:url value="/resources/signup.css" />/>
    <jsp:include page="LoginBar.jsp" />
</head>
<body>
    <article id="mainscreen">
        <h1>Sign up!</h1>

       <c:url value="/signup" var="reference"/>

        <form:form id="signupform" action="${reference}" method="post" modelAttribute="SignupForm">

            <form:label class="fieldlabel" path="username">Username:</form:label>
            <form:input class="field" path="username" type="text" />
            <form:errors class="fielderror" path="username" /><br>
            <form:label class="fieldlabel" path="password">Password:</form:label>
            <form:password class="field" path="password" />
            <form:errors class="fielderror" path="password" /><br>
            <form:label class="fieldlabel" path="password2">Confirm password:</form:label>
            <form:password class="field" path="password2" />
            <form:errors class="fielderror" path="password2" /><br>
            <form:button id="signupbutton" type="submit">Sign Up!</form:button>
        </form:form>
        <a id="mainref" href="<c:url value="/index"/>">Return to front page</a>
    </article>
</body>
</html>