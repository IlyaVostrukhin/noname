<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group">
    <form method="post">
        <div class="form-group">
            <label for="login">Логин</label>
            <input type="text" class="form-control" id="login" aria-describedby="loginHelp" placeholder="Введите логин">
            <small id="loginHelp" class="form-text text-muted">Введите логин, e-mail или номер телефона в формате
                +7-XXX-XXX-XX-XX</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword">Пароль</label>
            <input type="password" class="form-control" id="exampleInputPassword" placeholder="Пароль">
        </div>
        <button type="submit" class="btn btn-primary">Войти</button>
    </form>
</div>