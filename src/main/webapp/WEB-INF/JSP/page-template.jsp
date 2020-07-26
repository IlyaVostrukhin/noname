<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>NoName</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">
</head>
<body>
<header>
    <jsp:include page="fragment/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <aside>
            <jsp:include page="fragment/aside.jsp"/>
        </aside>
        <main>
            <jsp:include page="${currentPage}"/>
        </main>
    </div>
</div>
<footer>
    <jsp:include page="fragment/footer.jsp"/>
</footer>
<script src="${pageContext.request.contextPath}/static/js/app.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>
</body>
</html>
