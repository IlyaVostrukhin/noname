<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" uri="/WEB-INF/tags.tld" %>

<%--<div class="container">--%>
<h3 class="col-sm-12"><b>Регистрация</b></h3>
<h5 class="col-sm-12">Пожалуйста, заполните все обязательные поля для регистрации.</h5>
<h5 class="col-sm-12">Просим Вас заполнять предельно внимательно и корректно, чтобы мы могли оперативно с Вами
    связаться.</h5>
<hr>
<form accept-charset="UTF-8" action="/registration" method="post" enctype="application/x-www-form-urlencoded">
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="login">Логин</label>
            <input type="text" class="form-control" id="login" name="login" placeholder="Логин..." required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputEmail">Email</label>
            <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Email..." required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputPassword">Пароль</label>
            <input type="password" class="form-control" id="inputPassword" name="inputPassword"
                   placeholder="Password..." required>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="surName">Фамилия</label>
            <input type="text" class="form-control" id="surName" name="surName" placeholder="Иванов" required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="firstName">Имя</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Иван" required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="lastName">Отчество</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Иванович">
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-4 col-sm-12">
            <label for="phone">Номер телефона</label>
            <input type="text" class="form-control" id="phone" name="phone" placeholder="+7-999-999-99-99" required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="inputCity">Город</label>
            <input type="text" class="form-control" id="inputCity" name="inputCity" placeholder="Новосибирск" required>
        </div>
        <div class="form-group col-md-4 col-sm-12">
            <label for="postcode">Почтовый индекс</label>
            <input type="text" class="form-control" id="postcode" name="postcode"
                   placeholder="Должен соответствовать введенному адресу">
        </div>
        <div class="form-group col-md-12">
            <label for="inputAddress">Адрес</label>
            <input type="text" class="form-control" id="inputAddress" name="inputAddress"
                   placeholder="ул.3-я Строителей, д.25, кв.12"
                   required>
        </div>
    </div>
    <div class="form-group col-md-12">
        <div class="custom-checkbox">
            <label class="form-check-label">
                <input class="custom-control-input" type="checkbox"> Запомнить меня
            </label>
        </div>
    </div>
    <div class="form-row">
        <div class="form-group col-md-6" style="display: flex; align-items: center; justify-content: center;">
            <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
        </div>
        <div class="form-group col-md-6" style="display: flex; align-items: center; justify-content: center;">
            <a class="btn btn-primary">Восстановить доступ</a>
        </div>
    </div>
</form>
<%--</div>--%>