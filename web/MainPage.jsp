<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
    <fmt:setBundle basename="resources" var = "bundle"/>

</head>
<body>
    <nav>
        <ul>
            <c:choose>
                    <c:when test="${user != null}">
                        <c:choose>
                            <c:when test="${user.admin}">
                                <li><a href="/users"><fmt:message key="text.show_all_users" bundle="${bundle}"/> </a> </li>
                                <li><a href="/mainPage/addNewGame"><fmt:message key="text.add_game" bundle="${bundle}"/> </a> </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href = "#adsad" > <img src="${pageContext.request.contextPath}/images/user-regular.svg" height="15" width="15"> <fmt:message key="text.my_profile" bundle="${bundle}"/> </a>
                                    <ul>
                                        <li><a href=""><fmt:message key="text.my_friends" bundle="${bundle}"/> </a>
                                            <ul>
                                                <a href="/mainPage/myFriends/friends?status=1">Friends</a>
                                                <a href="/mainPage/myFriends/requests?status=0">Requests</a>
                                            </ul>
                                        </li>
                                        <li><a href="/mainPage/myGames"><fmt:message key="text.my_games" bundle="${bundle}"/> </a> </li>
                                        <li><a href="/mainPage/profile"><fmt:message key="text.my_profile" bundle="${bundle}"/> </a> </li>
                                    </ul>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <li><a href = "/mainPage/login" > <img src="${pageContext.request.contextPath}/images/user-regular.svg" height="15" width="15"><fmt:message key="text.login" bundle="${bundle}" /></a>
                        </li>
                    </c:otherwise>
            </c:choose>
            <li><a href = "/games" ><img src="${pageContext.request.contextPath}/images/gamepad-solid.svg" height="15" width="15"> <fmt:message key="text.games" bundle="${bundle}"/> </a></li>
            <li><a href = "/mainPage" ><img src="${pageContext.request.contextPath}/images/home-solid.svg" height="15" width="15"> <fmt:message key="text.home" bundle="${bundle}"/> </a></li>
        </ul>
    </nav>
    <c:if test="${currentAction != null}">
        <jsp:include page="${pageContext.request.contextPath}${currentAction}">
            <jsp:param name="carrentActionParam" value=""/>
        </jsp:include>
    </c:if>
</body>
</html>
