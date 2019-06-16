<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/CurrencyParserTag.tld" prefix="cur" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="resources" var = "bundle"/>
</head>
<body>
<c:if test="${operationStatus != null}">
    <c:choose>
        <c:when test="${!operationStatus.equals('Success')}">
            <div class="note" id = "error">
                    ${operationStatus}
            </div>
        </c:when>
        <c:otherwise>
            <div class="note" >
                    ${operationStatus}
            </div>
        </c:otherwise>
    </c:choose>

</c:if>
<nav>
        <ul>
            <c:choose>
                    <c:when test="${user != null}">
                        <c:choose>
                            <c:when test="${user.admin}">
                                <li><a href="/users"><fmt:message key="text.show_all_users" bundle="${bundle}"/> </a> </li>
                                <li><a href="/addNewGame"><fmt:message key="text.add_game" bundle="${bundle}"/> </a> </li>
                                <li><a href="/logout">Logout</a> </li>
                            </c:when>
                            <c:otherwise>
                                <li><a href = "" > <img src="${pageContext.request.contextPath}/images/user-regular.svg" height="15" width="15"> <fmt:message key="text.my_profile" bundle="${bundle}"/> </a>
                                    <ul>
                                        <li><a href=""><fmt:message key="text.my_friends" bundle="${bundle}"/> </a>
                                            <ul>
                                                <a href="/mainPage/myFriends/friends">Friends</a>
                                                <a href="/mainPage/myFriends/requests">Requests</a>
                                            </ul>
                                        </li>
                                        <li><a href="/mainPage/myGames"><fmt:message key="text.my_games" bundle="${bundle}"/> </a> </li>
                                        <li><a href="/profile"><fmt:message key="text.my_profile" bundle="${bundle}"/> </a> </li>
                                        <li><a href="/logout">Logout</a> </li>
                                    </ul>
                                </li>
                                <li><a href="/cart">CART</a></li>
                                <li><a href="/donatePage"> DONATE </a> </li>

                                <span class="currency">
                                    <cur:currency lan="${lan}">${user.money}</cur:currency>
                                </span>

                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <li><a href = "/login" ><img src="${pageContext.request.contextPath}/images/user-regular.svg" height="15" width="15"><fmt:message key="text.login" bundle="${bundle}" /></a>
                        </li>
                    </c:otherwise>
            </c:choose>

            <li><a href = "/mainPage" ><img src="${pageContext.request.contextPath}/images/home-solid.svg" height="15" width="15"> <fmt:message key="text.home" bundle="${bundle}"/> </a></li>



            <form action="/lan" method="get">
                <input type="hidden" name="lan" value="en_US">
                <input class="engEU" type="submit">
            </form>
            <form action="/lan" method="get">
                <input type="hidden" name="lan" value="ru_RU">
                <input class="ruEU" type="submit">
            </form>
        </ul>
    </nav>

</body>
</html>
