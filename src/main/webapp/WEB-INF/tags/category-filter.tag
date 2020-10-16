<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="categories" required="true" type="java.util.Collection" %>
<%@ attribute name="searchForm" required="true" type="dev.noname.form.SearchForm" %>

<div class="panel-heading">Фильтр по категории</div>
<div class="panel-body categories">
    <input type="checkbox" class="custom-checkbox" id="allCategories"><label for="allCategories"> Все </label>
    <c:forEach var="category" items="${categories }">
        <div class="form-group">
            <div class="checkbox">
                <input type="checkbox" name="category" id="${category.id}" value="${category.id }" ${searchForm.categories.contains(category.id) ? 'checked' : ''}
                              class="search-option custom-checkbox"><label for="${category.id}">${category.name } (${category.productCount })</label>
            </div>
        </div>
    </c:forEach>
</div>