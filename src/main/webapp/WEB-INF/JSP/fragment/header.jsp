<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<nav class="navbar navbar-default">
    <div class="container-fluid" style="height: 65px">
<%--    <div class="container-fluid" style="background-color: #595959">--%>
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ishopNav" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/products" style="color: #595959">
                <img alt="photo" src="../../../static/img/logo1.png" class="img-responsive visible-lg visible-md" style="height: 330%">
                <span class="visible-sm visible-xs">NoName</span>
            </a>
<%--            <a class="navbar-brand" href="/products" style="color: #595959">NoName</a>--%>
        </div>
        <div class="collapse navbar-collapse" id="ishopNav" style="margin: 8px">
            <ul id="currentShoppingCart" class="nav navbar-nav navbar-right ${CURRENT_SHOPPING_CART == null ? 'hidden' : ''}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Корзина <br><span class="total-count">Количество: ${CURRENT_SHOPPING_CART.totalCount}</span> шт<br>Сумма: ${CURRENT_SHOPPING_CART.totalCost} ₽<span
                            class="caret"></span>
                    </a>
                    <div class="dropdown-menu shopping-cart-desc">
                        Количество: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span><br>
                        Сумма: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span><br>
                        <a href="/shopping-cart" class="btn btn-primary btn-block">Детали заказа</a>
                    </div>
                </li>
            </ul>
            <br>
            <a href="#" class="btn btn-primary navbar-btn navbar-right sign-in"><!--<i class="fa fa-facebook-official"
                                                                                   aria-hidden="true"></i>--> Войти</a>
        </div>
    </div>
</nav>