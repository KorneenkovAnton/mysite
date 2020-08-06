<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="${pageContext.request.contextPath}/style.css" type="text/css" rel="stylesheet"/>
        <title>Profile</title>

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
    <jsp:include page="MainPage.jsp"/>
        <div class="profile">
            <form action="/updateUser" method="post">
                <div>
                    <label>ID ${user.id} </label>
                </div>

                <div>
                    <label for="login"><fmt:message key="text.login" bundle="${bundle}"/> </label>
                    <input id = "login" name="login" type="text" pattern="^[a-zA-Z][a-zA-Z0-9-]{1,20}$" maxlength="20" value="${user.login}" readonly/>
                </div>
                <div>
                    <label for="password"><fmt:message key="text.password" bundle="${bundle}"/> </label>
                    <input id = "password" name= "password"  type="password" minlength="8" pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$" value="${user.password}"/>
                </div>
                <div>
                    <label for="date"><fmt:message key="text.date_of_br" bundle="${bundle}"/> </label>
                    <input id = "date" type="date" name="dateOfBirthday" value="${user.dateOfBirthday}"/>
                </div>
                <div>
                    <label for="name"><fmt:message key="text.user_name" bundle="${bundle}"/> </label>
                    <input id = "name" type="text" name="name" minlength="1" pattern="^[а-яА-ЯёЁa-zA-Z '-]+$" value="${user.name}"/>
                </div>
                <div>
                    <label for="sname"><fmt:message key="text.user_s_name" bundle="${bundle}"/> </label>
                    <input id = "sname" type="text" name="sname"  minlength="1" pattern="^[а-яА-ЯёЁa-zA-Z '-]+$" value="${user.sName}"/>
                </div>
                <div>
                    <label for="email"><fmt:message key="text.eMail" bundle="${bundle}"/> </label>
                    <input id = "email" type="text" name="email" pattern="([.[^@\s]]+)@([.[^@\s]]+)\.([a-z]+)" value="${user.eMail}"/>
                </div>
                <div>
                    <label for="country"><fmt:message key="text.country" bundle="${bundle}"/> </label>
                    <input id = "country" type="text" name="country" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[\s-][а-яА-ЯёЁa-zA-Z]+)*$" value="${user.address.country}"/>
                </div>
                <div>
                    <label for="city"><fmt:message key="text.user_city" bundle="${bundle}"/> </label>
                    <input id = "city" type="text" name="city" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[\s-][а-яА-ЯёЁa-zA-Z]+)*$" value="${user.address.city}"/>
                </div>
                <div>
                    <label for="street"><fmt:message key="text.street" bundle="${bundle}"/> </label>
                    <input id = "street" type="text" name="street" pattern="^[а-яА-ЯёЁa-zA-Z]+(?:[\s-][а-яА-ЯёЁa-zA-Z]+)*$" value="${user.address.street}"/>
                </div>
                <div>
                    <label for="numberOfHouse"><fmt:message key="text.num_of_house" bundle="${bundle}"/> </label>
                    <input id = "numberOfHouse" type="text" name="numberOfHouse" value="${user.address.numberOfHouse}"/>
                </div>
                <div>
                    <input class="enter" type="submit" name="submit" value="UPDATE"/>
                </div>
            </form>
        </div>
    </body>
    </html>
