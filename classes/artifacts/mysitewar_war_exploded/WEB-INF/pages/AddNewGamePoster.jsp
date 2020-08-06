<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head><title>Title</title>
        <meta charset="UTF-8">
    </head>
    <body>
    <jsp:include page="MainPage.jsp"/>
    <form action="/addGameWithPoster" method="post" enctype="multipart/form-data">
        <input type="file" name="file" />
        <input class="enter" type="submit" name="submit" value="ADD"/>
    </form>
    </body>
    </html>

