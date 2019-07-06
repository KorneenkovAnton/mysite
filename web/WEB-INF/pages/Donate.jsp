
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/style.css" rel = "stylesheet" type="text/css">
</head>
<body>
<div class="gradient-border" id="box">
    <form action="/donate" method="post">
        <div>
            <input type="text" name="donate" placeholder="Currency">
        </div>
        <div>
            <input type="submit" name="submit" value="DONATE">
        </div>
    </form>
</div>
</body>
</html>
