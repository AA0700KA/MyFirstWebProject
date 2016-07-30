<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 17.07.2016
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
  <head>
    <title><fmt:message key="form.title.auth"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  </head>
  <body>
    <mytag:changelanguage url="/auth.jsp" />
    <jsp:include page="main_image.jsp"/>

    <div align="center">
        <c:if test="${requestScope.incorrect}">
            <p><fmt:message key="login.incorrect" /> </p>
        </c:if>
        <c:if test="${requestScope.blocked}">
            <p><fmt:message key="account.blocked" /> </p>
        </c:if>
     <form method="post" action="/forward">
       <input type="hidden" name="action" value="login"/>
       <table>
         <tr>
           <td><b><fmt:message key="login.label.username" /></b></td>
           <td><input type="text" name="login"/></td>
         </tr>
         <tr>
           <td><b><fmt:message key="login.label.password" /></b></td>
           <td><input type="password" name="password" /></td>
         </tr>
       </table>
       <fmt:message key="login.button.submit" var="bottonValue" />
       <input type="submit" name="submit" value="${bottonValue}" />
      </form>
    </div>
  </body>
</html>
