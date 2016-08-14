<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 23.07.2016
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title>MyPayments</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="../styles/payments_and_table.css" type="text/css"/>
</head>
<body>
<mytag:changelanguage url="/forwardMyPayments?action=my_payments" />
<jsp:include page="abonentPage.jsp"/>
    <div align="center" id="center_block">
        <fmt:message key="abonents.pay" var="pay"/>

        <c:if test="${requestScope.pay}">
            <p><fmt:message key="pay.succsessful" /> </p>
        </c:if>
        <c:if test="${requestScope.notPay}">
            <p><fmt:message key="pay.not.enought" /> </p>
        </c:if>

        <table>
            <tr>
                <td><fmt:message key="service.upper.case" /> </td>
                <td><fmt:message key="price.upper.case" /> </td>
                <td><fmt:message key="status" /> </td>
            </tr>
            <c:forEach var="payment" items="${payments}">
                <tr>
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
                    <td>
                        <c:if test="${not payment.paid}">

                            <form method="post" action="/forward">
                                <input type="hidden" name="action" value="pay"/>
                                <input type="hidden" name="price" value="${payment.price}"/>
                                <input type="hidden" name="service_id" value="${payment.service.id}">
                                <input type="submit" name="submit" value="${pay}"/>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
