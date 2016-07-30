<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/halloworldTag.tld" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 20.07.2016
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8" language="java" %>
<fmt:setLocale value="${not empty sessionScope.language ? sessionScope.language : 'en-EN'}"/>
<fmt:setBundle basename="finalproject.properties.text" />
<html>
<head>
    <title>Main page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>
<body>

<mytag:changelanguage url="/forward?action=main" />

   <div align="right">
       <p>${sessionScope.get("user").name}</p>
       <c:if test="${not empty sessionScope.user}">
           <p><a href="/forward?action=exit"><fmt:message key="main.exit"/> </a> </p>
       </c:if>
       <p><a href="/forward?action=myaccount"><fmt:message key="main.my.account"/> </a></p>
   </div>

<jsp:include page="main_image.jsp"/>


   <div align="center">
       <table>
           <tr>
               <td><a href="forward?action=main"><fmt:message key="main.main.services" /></a></td>
               <td><a href="#"><fmt:message key="main.connect" /> </a></td>
           </tr>
       </table>
   </div>
   <div align="center">

       <c:forEach items="${services}" var="service">
           <div>
               <p>${service.name}</p>

               <jstl:if test="${not empty sessionScope.user and not sessionScope.admin and service.id != 2}">

                   <form action="/forward" method="post">
                       <input type='hidden' name="id" value="${service.id}"/>
                       <input type="hidden" name="name" value="${service.name}"/>
                       <jstl:choose>
                           <jstl:when test="${empty sessionScope.services.get(service.id)}">
                               <input type="hidden" name="action" value="add_service"/>

                               <jstl:if test="${service.id eq 1}">
                                   <p><fmt:message key="main.internet.speed" /></p>
                                   <input type="number" name="speed" value="10"/>
                               </jstl:if>

                               <jstl:if test="${service.id eq 3}">
                                   <p><fmt:message key="main.videocall" /></p>
                                   <input type="checkbox" name="videocall"/>
                                   <p><fmt:message key="main.minutes.in.rouming" /></p>
                                   <input type="number" name="rouming-minutes" value="5"/>

                               </jstl:if>

                               <jstl:if test="${service.id eq 4}">
                                   <p><fmt:message key="main.count.channels" /></p>
                                   <p><input type="number" name="count_channels" value="10"/></p>
                               </jstl:if>
                               <fmt:message key="main.add" var="add" />
                               <p><input type="submit" name="submit" value="${add}"></p>

                           </jstl:when>
                           <jstl:otherwise>
                               <fmt:message key="services.remove" var="remove" />
                               <input type="hidden" name="action" value="remove_service"/>
                               <input type="submit" value="${remove}" name="submit"/>
                           </jstl:otherwise>
                       </jstl:choose>

                   </form>

               </jstl:if>

           </div>
       </c:forEach>
       

       
   </div>
</body>
</html>
