<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="login">
    <img src="${pageContext.request.contextPath}/images/user-regular.svg" width="100" height="100"/>
    <form action="/getUser" method="post">
        <div>
            <input type="text" name="login" placeholder="Login">
        </div>
        <div>
            <input type="password" name="password" placeholder="Password">
        </div>
        <div>
            <input class="enter" type="submit" name = "submit" value="ENTER">
        </div>
        <a href="/register" >Register</a>
    </form>
</div>
</body>
</html>
