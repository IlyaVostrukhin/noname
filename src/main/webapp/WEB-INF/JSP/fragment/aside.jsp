<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<div class="visible-xs-block xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#productCatalog">Product catalog <span class="caret"></span></a>
    <a data-toggle="collapse" href="#findProducts">Find products <span class="caret"></span></a>
</div>
<!-- Search form -->
<form class="search" action="/search">
<%--    <div id="findProducts" class="panel panel-primary collapse">--%>
    <div id="findProducts" class="panel panel-primary">
        <div class="panel-heading">Найти товар</div>
        <div class="panel-body">
            <div class="input-group">
                <input type="text" name="query" class="form-control" placeholder="Поиск...">
                <span class="input-group-btn">
                    <a id="goSearch" class="btn btn-default">Найти!</a>
                  </span>
            </div>
            <div class="more-options">
                <a data-toggle="collapse" href="#searchOptions" style="color: #595959">Еще фильтры <span class="caret"></span></a>
            </div>
        </div>
        <div id="searchOptions" class="collapse">
            <div class="panel-heading">Фильтр по категории</div>
            <div class="panel-body categories">
                <label> <input type="checkbox" id="allCategories"> Все </label>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="category" value="1" class="search-option">Трусы мужские
                            (12)</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="category" value="1" class="search-option">Белье женское
                            (7)</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="category" value="2" class="search-option">Наборы для мужчин
                            (5)</label>
                    </div>
                </div>
            </div>
            <div class="panel-heading">Фильтр по производителю</div>
            <div class="panel-body producers">
                <label> <input type="checkbox" id="allProducers"> Все </label>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="producer" value="1" class="search-option">Socks (14) </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="producer" value="2" class="search-option">Prestigio (7)
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="producer" value="2" class="search-option">Funny (2)
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- /Search form -->
<!-- Categories -->
<%--<div id="productCatalog" class="panel panel-success collapse">--%>
<div id="productCatalog" class="panel panel-primary">
    <div class="panel-heading">Категории товаров</div>
    <div class="list-group">
        <a href="/products" class="list-group-item"> <span class="badge">78</span> Трусы мужские</a>
        <a href="/products" class="list-group-item"> <span class="badge">75</span> Наборы для мужчин</a>
        <a href="/products" class="list-group-item"> <span class="badge">110</span> Носки мужские</a>
        <a href="/products" class="list-group-item"> <span class="badge">113</span> Женское белье</a>
        <a href="/products" class="list-group-item"> <span class="badge">216</span> Носки женские</a>
        <a href="/products" class="list-group-item"> <span class="badge">95</span> Ужас</a>
    </div>
</div>
<!-- /Categories -->