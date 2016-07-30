<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 21.07.2016
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />



<div align="center">
    <a href="/forward?action=main"><img src="../header_internet.png" alt="img"/></a>
</div>
    <div align="center">
    <table>
        <tr>
            <td><a href="/admin-page/registration.jsp"><fmt:message key="admin.add.user" /> </a></td>
            <td><a href="/forwardUsers?action=users"><fmt:message key="admin.all.users" /> </a></td>
            <td><a href="/forwardPayments?action=payments"> <fmt:message key="admin.payments" /> </a> </td>
        </tr>
    </table>
    </div>
    <c:if test="${requestScope.registerResponse}">
        <p><fmt:message key="register.succsessful" /> </p>
    </c:if>

