<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${CURRENT_SHOPPING_CART != null}">
        Total count = ${CURRENT_SHOPPING_CART.totalCount}<br>
        Products:<br>
        <c:forEach var="it" items="${CURRENT_SHOPPING_CART.items}">
            ${it.idProduct}-&gt;${it.count}<br>
        </c:forEach>
    </c:when>
    <c:otherwise>
        Shopping cart is null
    </c:otherwise>
</c:choose>