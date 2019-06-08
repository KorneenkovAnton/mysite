<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head><title>Friends</title>
        <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet"/>
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
    <form action="/addFriend" method="get">
        <input type="text" name="addFriendID" required = "required" pattern="[0-9]+">
        <input type="submit" value="ADD">
    </form>
        <c:forEach var="Friend" items="${user.friends}">
            <div class="friend">
                <div><span>ID = ${Friend.id}</span></div>
                <div><span>Name = ${Friend.name}</span></div>
                <div><span>Second Name =${Friend.sName}</span></div>
                <div><span>E-Mail = ${Friend.eMail}</span></div>
                <form method="get" action="/deleteFriend">
                    <input type="hidden" name="friendID" value="${Friend.id}"/>
                    <input type="submit" class="delete" value="DELETE"/>
                </form>
                <c:if test="${status == 0}">
                    <form action="/confirmFriend" method="get">
                        <input type="hidden" name="friendID" value="${Friend.id}"/>
                        <input type="submit" class="delete" value="ADD"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    </body>
    </html>

