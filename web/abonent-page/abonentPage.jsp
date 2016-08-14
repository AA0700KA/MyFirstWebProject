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
    <a href="/forward?action=main"><img src="../images/perth-best-internet-HEADER.jpg" alt="img" style="width: 80%"/></a>
</div>
    <div align="center" style="margin-top: 20px">
        <table style ="width:80%; background-color: #3E9ED8;">
            <tr>
                <td style="text-align: center;"><a href="/forwardMyPayments?action=my_payments" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="abonent.my.payments" /> </a></td>
                <td style="text-align: center;"><a href="/forward?action=my_data" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="abonent.my.data" /> </a> </td>
                <td style="text-align: center;"><a href="/abonent-page/fill_up_balance.jsp" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="abonent.fill.balance" /> </a> </td>
                <td style="text-align: center;"><a href="/forward?action=main" style="font-size: 120%;text-decoration: none; color: #fff; padding: 10px; display: inline-block; background-color: #3E9ED8"><fmt:message key="abonent.add.services" /> </a></td>
            </tr>
        </table>

    </div>

