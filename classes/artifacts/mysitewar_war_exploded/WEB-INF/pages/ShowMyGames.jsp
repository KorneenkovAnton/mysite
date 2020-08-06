<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Games</title>
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
<div class="wrapper">
<c:forEach  var="Game" items="${user.ownedGames}">
        <header class="header">
            <div><span>${Game.name}</span></div>
        </header>

        <div class="middle">

            <div class="container">
                <main class="content">
                    <div><span><fmt:message key="text.cost" bundle="${bundle}"/>   <cur:currency lan="${lan}">${Game.cost}</cur:currency></span></div>
                    <div><span><fmt:message key="text.release_date" bundle="${bundle}"/>:

                    <c:choose>
                        <c:when test="${lan != null}">
                            <c:if test="${lan.equals('ru_RU')}">
                                <fmt:formatDate value="${Game.releaseDate}" pattern="dd.MM.yyyy"/>
                            </c:if>
                            <c:if test="${lan.equals('en_US')}">
                                <fmt:formatDate value="${Game.releaseDate}" pattern="MM.dd.yyyy"/>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <fmt:formatDate value="${Game.releaseDate}" pattern="MM.dd.yyyy"/>
                        </c:otherwise>
                    </c:choose>

            </span></div>

                    <div><span><fmt:message key="text.descr" bundle="${bundle}"/> ${Game.description}</span></div>
                    <div><span><fmt:message key="text.dev" bundle="${bundle}"/> ${Game.developer}</span></div>

                    <div><span><fmt:message key="text.min_sys_req" bundle="${bundle}"/> </span></div>

                    <div><span><fmt:message key="text.OS" bundle="${bundle}"/> ${Game.minimalSystemRequirements.operationSystem}</span></div>
                    <div><span><fmt:message key="text.cpu_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuName}</span></div>
                    <div><span><fmt:message key="text.cpu_freq" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuFrequency}</span></div>
                    <div><span><fmt:message key="text.ram" bundle="${bundle}"/> ${Game.minimalSystemRequirements.ram}</span></div>
                    <div><span><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterName}</span></div>
                    <div><span><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterMemory}</span></div>
                    <div><span><fmt:message key="text.free_space" bundle="${bundle}"/> ${Game.minimalSystemRequirements.freeSpace}</span></div>

                    <div><span><fmt:message key="text.rec_sys_req" bundle="${bundle}"/> </span></div>

                    <div><span><fmt:message key="text.OS" bundle="${bundle}"/> ${Game.minimalSystemRequirements.operationSystem}</span></div>
                    <div><span><fmt:message key="text.cpu_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuName}</span></div>
                    <div><span><fmt:message key="text.cpu_freq" bundle="${bundle}"/> ${Game.minimalSystemRequirements.cpuFrequency}</span></div>
                    <div><span><fmt:message key="text.ram" bundle="${bundle}"/> ${Game.minimalSystemRequirements.ram}</span></div>
                    <div><span><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterName}</span></div>
                    <div><span><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> ${Game.minimalSystemRequirements.videoAdapterMemory}</span></div>
                    <div><span><fmt:message key="text.free_space" bundle="${bundle}"/> ${Game.minimalSystemRequirements.freeSpace}</span></div>

                </main>
            </div>

            <aside class="left-sidebar">
                <img src="${pageContext.request.contextPath}${Game.posterLink}" width="250px" height="450px"/>	</aside><!-- .left-sidebar -->

        </div>
</c:forEach>
</div>

</body>
</html>

