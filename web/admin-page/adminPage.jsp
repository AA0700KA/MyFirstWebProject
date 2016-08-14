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
    <a href="/forward?action=main"><img src="../images/header_internet.png" alt="img" style="width: 80%"/></a>
</div>
    <div align="center" style="margin-top: 20px">
    <table style="background-color: #3E9ED8; width: 80%">
        <tr>
            <td style="text-align: center"><a href="/admin-page/registration.jsp" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="admin.add.user" /> </a></td>
            <td style="text-align: center"><a href="/forwardUsers?action=users" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="admin.all.users" /> </a></td>
            <td style="text-align: center"><a href="/forwardPayments?action=payments" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"> <fmt:message key="admin.payments" /> </a></td>
        </tr>
    </table>
    </div>


