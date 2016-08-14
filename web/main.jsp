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
    <title><fmt:message key="main.main.page" /> </title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="styles/main_styles.css" type="text/css" />
</head>
<body>
   <mytag:changelanguage url="/forward?action=main" />
   <jsp:include page="main_discription.jsp" />

   <div align="center" id="service_block">

       <c:forEach items="${services}" var="service">

           <jstl:choose>
               <jstl:when test="${service.id eq 1}">
                   <jstl:set value="/images/Internet-marketing1.jpg" var="img_service" />
               </jstl:when>
               <jstl:when test="${service.id eq 2}">
                   <jstl:set value="/images/telefony.png" var="img_service" />
               </jstl:when>
               <jstl:when test="${service.id eq 3}">
                   <jstl:set value="/images/ip-telefony.png" var="img_service" />
               </jstl:when>
               <jstl:otherwise>
                   <jstl:set value="/images/television.jpg" var="img_service" />
               </jstl:otherwise>
           </jstl:choose>

           <div class="service_data">
               <p class="service_name">${service.name}</p>
               <p><img src="${img_service}" alt="service" /></p>

               <jstl:if test="${not empty sessionScope.user and not sessionScope.admin and service.id != 2}">

                   <form action="/forward" method="post">
                       <input type='hidden' name="id" value="${service.id}"/>
                       <input type="hidden" name="name" value="${service.name}"/>
                       <jstl:choose>
                           <jstl:when test="${empty sessionScope.services.get(service.id)}">
                               <input type="hidden" name="action" value="add_service"/>

                               <jstl:if test="${service.id eq 1}">
                                   <p class="label"><fmt:message key="main.internet.speed" /></p>
                                   <input type="number" name="speed" value="10"/>
                               </jstl:if>

                               <jstl:if test="${service.id eq 3}">
                                   <p class="label"><fmt:message key="main.videocall" /></p>
                                   <input type="checkbox" name="videocall"/>
                                   <p class="label"><fmt:message key="main.minutes.in.rouming" /></p>
                                   <input type="number" name="rouming-minutes" value="5"/>

                               </jstl:if>

                               <jstl:if test="${service.id eq 4}">
                                   <p class="label"><fmt:message key="main.count.channels" /></p>
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
