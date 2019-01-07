<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css"/>

    <title>WebPage!</title>
</head>
<body>

<div class="container">
    <div class="jumbotron main-content">
        <h1>Welcome to FFEE-Portal</h1>
        <p>The original Landon perseveres after 50 years in the heart of West London. The West End neighborhood has
            something for everyone from theater to dining to historic sights. And the not-to-miss Rooftop Cafe is a
            great place for travelers and locals to engage over drinks, food, and good conversation.</p>
    </div>
    <div class="container">
        <h3>What do you want to do?</h3>
        <ul>
            <li><a href="${contextPath}/home">View Invoices</a></li>
        </ul>
    </div>
</div>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.3/popper.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</body>
</html>