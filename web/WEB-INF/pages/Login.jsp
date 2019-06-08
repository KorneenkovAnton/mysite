<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
    <fmt:setBundle basename="resources" var = "bundle"/>
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
</head>
<body>
<jsp:include page="MainPage.jsp"/>
<div class="login">
    <img src="${pageContext.request.contextPath}/images/user-regular.svg" width="100" height="100"/>
    <form action="/getUser" method="post">
        <div>
            <input type="text" name="login" placeholder="Login" required = "required" pattern="^[a-zA-Z][a-zA-Z0-9-]{1,20}$">
        </div>
        <div>
            <input type="password" name="password" placeholder="Password" required = "required" pattern="^(?=.*\d)(?=.*[a-z])(?!.*\s).*$"
            minlength="8">
        </div>
        <div>
            <input class="enter" type="submit" name = "submit" value="ENTER">
        </div>
        <a href="/register" >Register</a>

    </form>
</div>
</body>
</html>
