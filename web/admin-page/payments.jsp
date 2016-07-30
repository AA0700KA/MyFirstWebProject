<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 22.07.2016
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title><fmt:message key="admin.payments" /> </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
   <mytag:changelanguage url="/forwardPayments?action=payments" />
   <jsp:include page="adminPage.jsp" />
     <div align="center">
    <form action="/forwardPayments" method="post">
        <input type="hidden" name="action" value="payments">
        <select name="status">
            <option selected value="all"><fmt:message key="admin.payments.all" /> </option>
            <option value="true"><fmt:message key="admin.payments.paid"/> </option>
            <option value="false"><fmt:message key="admin.payments.not.paid" /> </option>
        </select>
        <fmt:message key="admin.payments.find" var="find" />
        <input type="submit" value="${find}">
    </form>
    <table>
        <tr>
            <td><fmt:message key="id" /> </td>
            <td><fmt:message key="login.lower.case" /> </td>
            <td><fmt:message key="name.lower.case" /> </td>
            <td><fmt:message key="service.lower.case" /> </td>
            <td><fmt:message key="admin.payments.summa" /> </td>
            <td><fmt:message key="status" /> </td>
        </tr>
        <c:forEach items="${payments}" var="payment">
            <tr>
                <td>${payment.user.id}</td>
                <td>${payment.user.login}</td>
                <td>${payment.user.name}</td>
                <td>${payment.service.name}</td>
                <td>${payment.price}</td>
                <td>
                    <c:choose>
                        <c:when test="${payment.paid}">
                            <fmt:message key="admin.payments.paid" />
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="admin.payments.not.paid" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </table>
     </div>
</body>
</html>
