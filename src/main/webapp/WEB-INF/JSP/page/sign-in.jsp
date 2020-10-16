<%@ page import="dev.noname.Constants" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="u" uri="/WEB-INF/tags.tld" %>

<div class="form-group">
    <h4 align="center"><b>Рады приветствовать Вас в нашем магазине!</b></h4>
    <h5 align="center"><b>Для продолжения авторизуйтесь или зарегистрируйтесь в системе.</b></h5><br/>
    <form method="post">
        <div class="form-group">
            <label for="login">Логин</label>
            <input type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp"
                   placeholder="Введите логин">
            <small id="loginHelp" class="form-text text-muted">Введите логин, e-mail или номер телефона в формате
                +7-999-999-99-99</small>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword">Пароль</label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword"
                   placeholder="Пароль">
        </div>

        <c:if test="${error==true}">
            <div class="alert alert-danger" role="alert">
                Пользователь с таким именем и паролем не найден!
                <%
                    session.setAttribute("error", false);
                %>
            </div>
        </c:if>
        <u:urlEncode url="${target }" var="encodedUrl"/>
        <button type="submit" class="btn btn-primary">Войти</button>
        <a href="/registration?target=${encodedUrl }" class="btn btn-primary">Регистрация</a>
    </form>
</div>