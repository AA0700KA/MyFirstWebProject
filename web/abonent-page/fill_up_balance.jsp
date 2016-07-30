<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 23.07.2016
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title>Fill Balance</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>
<mytag:changelanguage url="/abonent-page/fill_up_balance.jsp" />
<jsp:include page="abonentPage.jsp" />
   <div align="center">
   <form action="/forward" method="post">
       <input type="hidden" name="action" value="fill_balance"/>
       <table>
           <tr>
               <td><label><fmt:message key="abonent.fill.balance.update" /> </label></td>
               <td><input type="number" name="update_balance"/></td>
           </tr>
           <tr>
               <fmt:message key="abonent.fill" var="fill" />
               <td colspan="2"><input type="submit" name="submit" value="${fill}" /></td>
           </tr>
       </table>

   </form>
   </div>
</body>
</html>
