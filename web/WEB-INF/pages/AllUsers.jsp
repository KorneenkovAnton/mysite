<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
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
        <c:forEach  var="User" items="${users}">
            <div class="usersAdminPage">
                <div><span><fmt:message key="text.user_id" bundle="${bundle}"/> = ${User.id}</span></div>
                <div><span><fmt:message key="text.user_name" bundle="${bundle}"/> = ${User.name}</span></div>
                <div><span><fmt:message key="text.user_s_name" bundle="${bundle}"/> = ${User.sName}</span></div>
                <div><span><fmt:message key="text.eMail" bundle="${bundle}"/> = ${User.eMail}</span></div>
                <div><span><fmt:message key="text.date_of_br" bundle="${bundle}"/> = ${User.dateOfBirthday}</span></div>
                <div><span><fmt:message key="text.country" bundle="${bundle}"/> = ${User.address.country}</span></div>
                <div><span><fmt:message key="text.user_city" bundle="${bundle}"/> = ${User.address.city}</span></div>
                <form action="/deleteUser" method="get">
                    <input type="hidden" name = "deletingUser" value="${User.id}"/>
                    <input type="hidden" name = "deletingAddress" value="${User.address.id}"/>
                    <input type="submit" class="delete" value="DELETE"/>
                </form>
                <c:if test="${!User.admin}">
                    <form action="/setAdmin" method="get">
                        <input type="hidden" name="newAdminId" value="${User.id}"/>
                        <input type="submit" class="delete" value="SET ADMIN ROLE"/>
                    </form>
                </c:if>
            </div>
        </c:forEach>
    <div class="center">
        <div class="pagination">
            <c:if test="${currentPage != 1}">
                <a href="/users?currentPage=${currentPage-1}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${countOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <a>${i}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="/users?currentPage=${i}">${i}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </div>
    </body>

