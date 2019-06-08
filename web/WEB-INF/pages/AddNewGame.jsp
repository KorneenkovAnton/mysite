<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head><title>AddGame</title>
        <link href = "../../style.css" rel = "stylesheet" type = "text/css"/>
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
    <div class="addGame">
        <form action="/addGame" method="post">
            <div>
                <label for="name"><fmt:message key="text.name" bundle="${bundle}"/> </label>
                <input id ="name" type="text" required="required" name="name" pattern="^[a-zA-Z]+$"/>
            </div>
            <div>
                <label for="desc"><fmt:message key="text.cost" bundle="${bundle}"/> </label>
                <input id = "cost" type="text" name="cost" pattern="[0-9]{,3}"/>
            </div>
            <div>
                <label for="desc"><fmt:message key="text.release_date" bundle="${bundle}"/> </label>
                <input id = "date" type="date" name="releaseDate"/>
            </div>
            <div>
                <label for="desc"><fmt:message key="text.descr" bundle="${bundle}"/> </label>
                <input id = "desc" type="text" name="description" pattern="^[a-zA-Z]+$" size="400"/>
            </div>
            <div>
                <label for="desc"><fmt:message key="text.dev" bundle="${bundle}"/> </label>
                <input id = "dev" type="text" name="developer" required="required" pattern="^[a-zA-Z0-9]+$"/>
            </div>

            <label><fmt:message key="text.min_sys_req" bundle="${bundle}"/></label><br/><br/>


        <div class="minSys">
            <div>
                <label for="operSysMin"><fmt:message key="text.OS" bundle="${bundle}"/> </label>
                <input id = "operSysMin" type="text" name="operationSystemMin" required="required" pattern="^[a-zA-Z]+$"/>
            </div>
            <div>
                <label for="cpuNameMin"><fmt:message key="text.cpu_name" bundle="${bundle}"/></label>
                <input id = "cpuNameMin" type="text" name="cpuNameMin" required="required" pattern="^[a-zA-Z]+$"/>
            </div>
            <div>
                <label for="cpruFreqMin"><fmt:message key="text.cpu_freq" bundle="${bundle}"/> </label>
                <input id = "cpruFreqMin" type="text" name="cpuFrequencyMin" required="required" pattern="\d+(\.\d{1})?"/>
            </div>
            <div>
                <label for="ramMin"><fmt:message key="text.ram" bundle="${bundle}"/> </label>
                <input id = "ramMin" type="text" name="ramMin" required="required" pattern="^[ 0-9]+$"/>
            </div>
            <div>
                <label for="adapterNameMin"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> </label>
                <input id = "adapterNameMin" type="text" name="videoAdapterNameMin" required="required" pattern="^[a-zA-Z]+$"/>
            </div>
            <div>
                <label for="adapterMemMin"><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> </label>
                <input id = "adapterMemMin" type="text" name="videoAdaptermMemoryMin" required="required" pattern="^[ 0-9]+$"/>
            </div>
            <div>
                <label for="freeSpaceMin"><fmt:message key="text.free_space" bundle="${bundle}"/> </label>
                <input id = "freeSpaceMin" type="text" name="freeSpaceMin" required="required" pattern="^[ 0-9]+$"/>
            </div>
        </div>

        <label><fmt:message key="text.rec_sys_req" bundle="${bundle}"/> </label><br/><br/>


    <div class="recSys">
        <div>
            <label><fmt:message key="text.OS" bundle="${bundle}"/> </label>
            <input type="text" name="operationSystemRec" required="required" pattern="^[a-zA-Z]+$"/>
        </div>
        <div>
            <label><fmt:message key="text.cpu_name" bundle="${bundle}"/> </label>
            <input  type="text" name="cpuNameRec" required="required" pattern="^[a-zA-Z]+$"/>
        </div>
        <div>
            <label><fmt:message key="text.cpu_freq" bundle="${bundle}"/> </label>
            <input type="text" name="cpuFrequencyRec" required="required" pattern="\d+(\.\d{1})?"/>
        </div>
        <div>
            <label><fmt:message key="text.ram" bundle="${bundle}"/> </label>
            <input  type="text" name="ramRec" required="required" pattern="^[ 0-9]+$"/>
        </div>
        <div>
            <label for="adapterNameMin"><fmt:message key="text.video_adapter_name" bundle="${bundle}"/> </label>
            <input  type="text" name="videoAdapterNameRec" required="required" pattern="^[a-zA-Z]+$"/>
        </div>
        <div>
            <label for="adapterMemMin"><fmt:message key="text.video_adapter_memory" bundle="${bundle}"/> </label>
            <input  type="text" name="videoAdaptermMemoryRec" required="required" pattern="^[ 0-9]+$"/>
        </div>
        <div>
            <label for="freeSpaceMin"><fmt:message key="text.free_space" bundle="${bundle}"/> </label>
            <input  type="text" name="freeSpaceRec" required="required" pattern="^[ 0-9]+$"/>
        </div>
    </div>
    <input class="enter" type="submit" name="submit" value="ADD"/>
    </form>
    </div>
    </body>
</html>
