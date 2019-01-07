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

    <title>Login!</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body">
                    <h5 class="card-title text-center">Sign In</h5>
                    <form class="form-signin" action="${contextPath}/login" method="post">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                Invalid username or password.
                            </div>
                        </c:if>
                        <div class="form-label-group">
                            <label for="inputEmail">User</label>
                            <input type="text" id="inputEmail" name="username" class="form-control" required autofocus>
                        </div>

                        <div class="form-label-group">
                            <label for="inputPassword">Password</label>
                            <input type="password" id="inputPassword" name="password" class="form-control" required>
                        </div>

                        <hr class="my-4">

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.3/popper.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</body>
</html>