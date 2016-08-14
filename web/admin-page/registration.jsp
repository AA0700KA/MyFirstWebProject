<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 21.07.2016
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title>Registration</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="../styles/register_styles.css" type="text/css"/>
</head>
<body>
  <mytag:changelanguage url="/admin-page/registration.jsp" />
  <jsp:include page="adminPage.jsp"/>

    <div align="center" id="register">

        <c:if test="${requestScope.incorrect}">
            <p><fmt:message key="register.incorrect" /></p>
        </c:if>
        <c:if test="${requestScope.exists}">
            <p><fmt:message key="register.exists" /></p>
        </c:if>
        <c:if test="${requestScope.registerResponse}">
            <p><fmt:message key="register.succsessful" /> </p>
        </c:if>

        <form action="/forward" method="post">
            <input type="hidden" name="action" value="register" />
        <table>

            <tr>
                <td><b><fmt:message key="admin.registration.name" /></b></td>
                <td><input type="text" name = "name"/></td>
            </tr>
            <tr>
                <td><b><fmt:message key="login.label.username"/> </b></td>
                <td><input type="text" name = "login"/></td>
            </tr>
            <tr>
                <td><b><fmt:message key="login.label.password" /> </b></td>
                <td><input type="password" name = "password"/></td>
            </tr>

        </table>
            <fmt:message key="admin.registration" var="register" />
            <input type="submit" name="submit" value="${register}">
        </form>

    </div>
</body>
</html>
