<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 21.07.2016
  Time: 13:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />


<div align="center">
    <a href="/forward?action=main"><img src="../perth-best-internet-HEADER.jpg" alt="img"/></a>
</div>
    <div align="center">
        <table>
            <tr>
                <td><a href="/forwardMyPayments?action=my_payments"><fmt:message key="abonent.my.payments" /> </a></td>
                <td><a href="/forward?action=my_data"><fmt:message key="abonent.my.data" /> </a> </td>
                <td><a href="/abonent-page/fill_up_balance.jsp"><fmt:message key="abonent.fill.balance" /> </a> </td>
                <td><a href="/forward?action=main"><fmt:message key="abonent.add.services" /> </a></td>
            </tr>
        </table>

            <c:if test="${requestScope.pay}">
                <p><fmt:message key="pay.succsessful" /> </p>
            </c:if>
            <c:if test="${requestScope.notPay}">
                <p><fmt:message key="pay.not.enought" /> </p>
            </c:if>
            <c:if test="${requestScope.balance}">
                <p><fmt:message key="balance.updated" /> </p>
            </c:if>
    </div>

