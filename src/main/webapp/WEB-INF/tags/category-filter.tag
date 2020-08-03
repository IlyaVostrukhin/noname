<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="categories" required="true" type="java.util.Collection" %>
<%@ attribute name="searchForm" required="true" type="dev.noname.form.SearchForm" %>

<div class="panel-heading">Фильтр по категории</div>
<div class="panel-body categories">
    <label> <input type="checkbox" id="allCategories"> Все </label>
    <c:forEach var="category" items="${categories }">
        <div class="form-group">
            <div class="checkbox">
                <label><input type="checkbox" name="category" value="${category.id }"
                    ${searchForm.categories.contains(category.id) ? 'checked' : ''}
                              class="search-option">${category.name } (${category.productCount })</label>
            </div>
        </div>
    </c:forEach>
</div>