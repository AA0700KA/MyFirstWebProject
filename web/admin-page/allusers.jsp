<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 21.07.2016
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title><fmt:message key="admin.all.users" /> </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="../styles/payments_and_table.css" type="text/css" />
</head>
<body>
<mytag:changelanguage url="/forwardUsers?action=users" />
<jsp:include page="adminPage.jsp"/>
<div align="center" id="center_block">
    <table>
        <tr>
            <td><fmt:message key="id" /> </td>
            <td><fmt:message key="login.lower.case" /> </td>
            <td><fmt:message key="name.lower.case" /> </td>
            <td><fmt:message key="balance" /> </td>
            <td><fmt:message key="status" /> </td>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.login}</td>
                <td>${user.name}</td>
                <td>${user.balance}</td>
                <td>
                    <c:choose>
                    <c:when test="${user.blocked}">
                       <fmt:message key="user.blocked" />
                    </c:when>
                    <c:otherwise>
                        <fmt:message key="user.not.blocked" />
                    </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <form action="forwardUsers" method="post">
                        <input type="hidden" name="action" value="block_user">
                        <input type="hidden" name="id" value="${user.id}">
                        <fmt:message key="user.block" var="block" />
                        <fmt:message key="user.unblock" var="unblock" />
                        <c:choose>
                            <c:when test="${user.blocked}">
                                <input type="submit" name="submit" value="${unblock}">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" name="submit" value="${block}">
                            </c:otherwise>
                        </c:choose>

                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
