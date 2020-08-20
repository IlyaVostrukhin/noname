<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="noname" tagdir="/WEB-INF/tags" %>
<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<div class="visible-xs-block xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog" style="color: #0f0f0f">Каталог товаров <span class="caret"></span></a>
    <a data-toggle="collapse" href="#findProducts" style="color: #0f0f0f">Поиск товара <span class="caret"></span></a>
</div>
<!-- Search form -->
<form class="search" action="/search" enctype="application/x-www-form-urlencoded">
    <div id="findProducts" class="panel panel-primary collapse">
        <div class="panel-heading">Найти товар</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Поиск..."
                       value="${searchForm.query }">
                <span class="input-group-btn">
                    <a id="goSearch" class="btn btn-default">Найти!</a>
                  </span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions" style="color: #595959">Поиск по фильтрам <span
                        class="caret"></span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse ${searchForm.categoriesNotEmpty or searchForm.producersNotEmpty ? 'in' : '' }">
            <noname:category-filter categories="${category_list }" searchForm="${searchForm }"/>
            <noname:producer-filter producers="${producer_list }" searchForm="${searchForm }"/>
        </div>
    </div>
</form>
<!-- /Search form -->
<!-- Categories -->
<%--<div id="productCatalog" class="panel panel-success collapse">--%>
<div id="productCatalog" class="panel panel-primary collapse">
    <div class="panel-heading">Категории товаров</div>
    <div class="list-group">
        <c:forEach var="category" items="${category_list }">
            <a href="/products${category.url == selectedCategoryUrl ? '' : category.url}"
               class="list-group-item ${selectedCategoryUrl == category.url ? 'active' : ''}">
                <span class="badge">${category.productCount}</span> ${category.name }
            </a>
        </c:forEach>

    </div>
</div>
<!-- /Categories -->