<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cur" uri="/WEB-INF/CurrencyParserTag.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>

    <link href = "${pageContext.request.contextPath}/style.css" rel = "stylesheet" type = "text/css"/>
</head>
<body>
<jsp:include page="MainPage.jsp"/>

<div class="shopping-cart">
    <div class="title">
        Shopping Bag
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
                                <span>${game.releaseDate}</span>
                                <span>${game.developer}</span>
                            </div>
                            <div class="total-price">${game.cost}</div>
                        </div>
                    </c:forEach>
                    <div class="buttons">
                        <form action="/buyGames" method="post">
                            <input type="submit" class="delete" value="BUY">
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    Cart is Empty
                </c:otherwise>
            </c:choose>
</div>
</body>
</html>
