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
                            <div class="mdl-card__supporting-text">
                                <c:if test="${rubbish == null}">
                                    <form name="form" action="insert" method="post" onsubmit="return validateForm()"/>
                                </c:if>
                                <c:if test="${rubbish != null}">
                                    <form name="form" action="update" method="post" onsubmit="return validateForm()"/>
                                </c:if>
                                <c:if test="${rubbish != null}">
                                    <input type="hidden" name="id" value="<c:out value='rubbish.id' />" />
                                </c:if>

                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="text" name="name" value="<c:out value='rubbish.name'/>" id="name"/>
                                    <label class="mdl-textfield__label" for="name">Name</label>
                                </div>

                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="text" name="description" value="<c:out value='rubbish.description'/>" id="description"/>
                                    <label class="mdl-textfield__label" for="description">Description</label>
                                </div>

                                <div>
                                    <c:choose>
                                        <c:when test="${rubbish != null}">
                                            <input class="mdl-textfield__input" type="text" name="quantity" value="<c:out value='rubbish.quantity'/>" id="quantity"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="mdl-textfield__input" type="text" name="quantity" value="<c:out value='1'/>" id="quantity"/>
                                        </c:otherwise>
                                    </c:choose>
                                    <label class="mdl-textfield__label" for="quantity">Quantity</label>
                                </div>

                                <div>
                                    <input type="submit" class="mdl-button mdl-button--colored mdl-js-button mdl-js-ripple-effect" value="save">
                                </div>
<%--                                </form>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
    <script type="text/javascript">
        function validateForm() {
            let x = document.forms["form"]['quantity'].value;
            if(x === "") {
                alert("Quantity must be filled out");
                return false;
            }
        }
    </script>
</body>
</html>
