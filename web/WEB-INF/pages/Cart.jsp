<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cur" uri="/WEB-INF/CurrencyParserTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <meta charset="UTF-8">
    <c:choose>
        <c:when test="${lan != null}">
            <fmt:setLocale value="${lan}"/>
        </c:when>
        <c:otherwise>
            <fmt:setLocale value="en_US"/>
        </c:otherwise>
    </c:choose>
    <fmt:setBundle basename="resources" var = "bundle"/>
    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
</head>
<body>

<jsp:include page="MainPage.jsp"/>

<div class="shopping-cart">
    <div class="title">
        <fmt:message key="text.shopping_bag" bundle="${bundle}"/>
    </div>
            <c:choose>
                <c:when test="${cart != null}">
                    <c:forEach var="game" items="${cart}">

                        <div class="item">
                            <div class="buttons">
                                <form action="/deleteFromCart" method="post">
                                    <input type="hidden" name="deleteCart" value="${game.id}">
                                    <input type="submit" class="delete" value="DELETE">
                                </form>
                            </div>

                            <div class="image">
                                <img src="${pageContext.request.contextPath}${game.posterLink}" alt="" width="70px" height="70px"/>
                            </div>

                            <div class="description">
                                <span>${game.name}</span>
                                <span>
                                    <c:choose>
                                        <c:when test="${lan != null}">
                                            <c:if test="${lan.equals('ru_RU')}">
                                                <fmt:formatDate value="${game.releaseDate}" pattern="dd.MM.yyyy"/>
                                            </c:if>
                                            <c:if test="${lan.equals('en_US')}">
                                                <fmt:formatDate value="${game.releaseDate}" pattern="MM.dd.yyyy"/>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatDate value="${game.releaseDate}" pattern="MM.dd.yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </span>
                                <span>${game.developer}</span>
                            </div>
                            <div class="total-price"><fmt:message key="text.cost" bundle="${bundle}"/>
                                <cur:currency lan="${lan}">${game.cost}</cur:currency>
                            </div>
                        </div>
                    </c:forEach>
                    <div class="buttons">
                        <form action="/buyGames" method="post">
                            <input type="submit" class="delete" value="BUY">
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <fmt:message key="text.empty_cart" bundle="${bundle}"/>
                </c:otherwise>
            </c:choose>
</div>
</body>
</html>
