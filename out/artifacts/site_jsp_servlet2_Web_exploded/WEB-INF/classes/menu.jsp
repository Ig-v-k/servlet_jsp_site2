<%--
  Created by IntelliJ IDEA.
  User: wykladowca
  Date: 12.05.2019
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@include file="head.jsp"%>
</head>
<body>

    <%@page import="java.util.Calendar" %>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix="tag" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <c:set var="now" value="<%=new java.util.Date()%>"/>
    <header class="mdl-layout__header">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout__title">Management System</span>
            <div class="mdl-layout__spacer" style="padding-right: inherit;"></div>
            <tag:formatDate pattern="dd-MM-yyyy HH:mm" value="${now}" var="nowString"/>
            <c:out value="${nowString}"/>
            <nav class="mdl-navigation mdl-layout--large-screen-only" style="margin: auto;">
                <a class="mdl-navigation__link" href="<c:url value="/new"/>">Add new Rubbish</a>
                <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/list">List all Rubbish</a>
            </nav>
        </div>
    </header>
    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">MS</span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/new">Add new Rubbish</a>
            <a class="mdl-navigation__link" href="${pageContext.request.contextPath}/list">List all Rubbish</a>
        </nav>
    </div>

</body>
</html>
