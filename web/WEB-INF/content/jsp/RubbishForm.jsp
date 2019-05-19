<%--
  Created by IntelliJ IDEA.
  User: wykladowca
  Date: 12.05.2019
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="en">
<%@include file="../jsp/head.jsp"%>
<body>
    <div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">
        <%@include file="../jsp/menu.jsp"%>
        <main class="mdl-layout__content">
            <div class="page-content">
                <div class="mdl-grid center-items">
                    <div class="mdl-cell mdl-cell--4-col">
                        <div class="mdl-card mdl-shadow--6dp">
                            <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                                <h2 class="mdl-card__title-text">
                                    <c:if test="${rubbish == null}">Add new Rubbish</c:if>
                                    <c:if test="${rubbish != null}">Edit Rubbish</c:if>
                                </h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>
