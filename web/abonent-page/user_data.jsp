<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 23.07.2016
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="../styles/payments_and_table.css" type="text/css" />
</head>
<body>
    <mytag:changelanguage url="/forward?action=my_data" />
    <jsp:include page="abonentPage.jsp"/>
    <div align="center" id="center_block">
        <table>
            <tr>
                <td><fmt:message key="id" /> </td>
                <td>${sessionScope.user.id}</td>
            </tr>
            <tr>
                <td><fmt:message key="login.label.username" /> </td>
                <td>${sessionScope.user.login}</td>
            </tr>
            <tr>
                <td><fmt:message key="admin.registration.name" /> </td>
                <td>${sessionScope.user.name}</td>
            </tr>
            <tr>
                <td><fmt:message key="main.main.services" /> </td>
                <td>
                    <table>
                    <c:forEach items="${sessionScope.services.values()}" var="service">
                        <tr>
                            <td>${service.name}</td>
                            <td>${service.getCharacteristics()}</td>
                        </tr>
                    </c:forEach>
                    </table>
                </td>
            </tr>
            <tr>
                <td><fmt:message key="balance" /></td>
                <td>${sessionScope.user.balance}</td>
            </tr>
        </table>

    </div>
</body>
</html>
