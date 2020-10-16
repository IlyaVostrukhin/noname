<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" uri="/WEB-INF/tags.tld" %>

<nav class="navbar navbar-default" style="height: 90px">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ishopNav"
                    aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/products" style="color: #595959">
                <img alt="photo" src="../../../static/img/logo1.png" class="img-responsive visible-lg visible-md"
                     style="height: 65px">
                <span class="visible-sm visible-xs">NoName</span>
            </a>
            <%--            <a class="navbar-brand" href="/products" style="color: #595959">NoName</a>--%>
        </div>
        <div class="collapse navbar-collapse" id="ishopNav" style="margin: 8px">
            <ul id="currentShoppingCart"
                class="nav navbar-nav navbar-right ${CURRENT_SHOPPING_CART == null ? 'hidden' : ''}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Корзина <br>
                        Количество: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span> шт <br>
                        Сумма: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span> ₽
                        <span class="caret"></span>
                    </a>
                    <div class="dropdown-menu shopping-cart-desc">
                        Количество: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span><br>
                        Сумма: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span><br>
                        <a href="/shopping-cart" class="btn btn-primary btn-block">Детали заказа</a>
                    </div>
                </li>
            </ul>
            <c:choose>
                <c:when test="${CURRENT_ACCOUNT != null}">
                    <ul class="nav navbar-nav navbar-right">
                        <li style="vertical-align: top; text-align: right"><a href="/orders">Добро пожаловать,
                            <br>${CURRENT_ACCOUNT.name}!</a></li>
                        <li style="vertical-align: top;"><a href="/orders">Мои заказы</a></li>
                        <li style="vertical-align: top;">
                            <a href="javascript:void(0);" class="post-request" data-url="/sign-out">Выйти</a>
                        </li>
                    </ul>
                </c:when>
                <c:otherwise>
                    <c:if test="${fn:startsWith(CURRENT_REQUEST_URL,'/search') or fn:startsWith(CURRENT_REQUEST_URL, '/products') or
                    						CURRENT_REQUEST_URL == '/shopping-cart' }">
                        <u:urlEncode url="${CURRENT_REQUEST_URL }" var="encodedUrl"/>
                    </c:if>
                    <c:if test="${target != null}">
                        <u:urlEncode url="${target }" var="encodedUrl"/>
                    </c:if>
                    <ul class="nav navbar-nav navbar-right">
                        <li style="vertical-align: top;"><a href="/sign-in?target=${encodedUrl}">Войти</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>