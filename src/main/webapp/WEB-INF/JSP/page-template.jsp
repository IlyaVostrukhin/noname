<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>NoName</title>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/app.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap-theme.css">--%>
<%--    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.css">--%>
    <link href="/static/css/bootstrap.css" rel="stylesheet">
    <link href="/static/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/static/css/font-awesome.css" rel="stylesheet">
    <link href="/static/css/app.css" rel="stylesheet">
</head>
<body>
<header>
    <jsp:include page="fragment/header.jsp"/>
</header>
<div class="container-fluid">
    <div class="row">
        <aside class="col-xs-12 col-sm-4 col-md-3 col-lg-2">
            <jsp:include page="fragment/aside.jsp"/>
        </aside>
        <main class="col-xs-12 col-sm-8 col-md-9 col-lg-10">
            <jsp:include page="${currentPage}"/>
        </main>
    </div>
</div>
<footer class="footer">
    <jsp:include page="fragment/footer.jsp"/>
</footer>
<script src="/static/js/jquery.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/app.js"></script>
<%--<script src="${pageContext.request.contextPath}/static/js/app.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/static/js/jquery.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/static/js/bootstrap.js"></script>--%>
</body>
</html>