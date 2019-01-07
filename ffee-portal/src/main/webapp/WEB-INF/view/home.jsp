<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="webjars/bootstrap/4.2.1/css/bootstrap.min.css"/>

    <title>Home</title>
</head>
<body>

<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
    <div class="container">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item"><a class="nav-link" href="#mission">Mission</a></li>
            <li class="nav-item"><a class="nav-link" href="#services">Services</a></li>
            <li class="nav-item"><a class="nav-link" href="#staff">Staff</a></li>
            <li class="nav-item"><a class="nav-link" href="#testimonials">Testimonials</a></li>
        </ul><!-- navbar-nav -->

        <ul class="navbar-nav">
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/logout">Logout</a>
                </li>
            </sec:authorize>
        </ul><!-- navbar-nav -->
    </div><!-- container -->
</nav>

<div class="container">

    <div class="content" id="mission">
        <h2>Our Mission</h2>

        <p>Wisdom Pet Medicine strives to blend the best in traditional and alternative medicine in the diagnosis and
            treatment of companion animals including dogs, cats, birds, reptiles, rodents, and fish. We apply the wisdom
            garnered in the centuries old tradition of veterinary medicine, to find the safest treatments and&nbsp;cures.</p>

        <p>We strive to be your pet's medical experts from youth through the senior years. We build preventative health
            care plans for each and every one of our patients, based on breed, age, and sex, so that your pet receives
            the most appropriate care at crucial milestones. We want to give your pet a long and healthy&nbsp;life.</p>
    </div>

    <div class="content" id="services">
        <h2>Services</h2>

        <section>
            <h3>Exotic Pets</h3>
            <p>We offer specialized care for reptiles, rodents, birds, and other exotic pets.</p>
        </section>

        <section>
            <h3>Grooming</h3>
            <p>Our therapeutic grooming treatments help battle fleas, allergic dermatitis, and other challenging skin
                conditions.</p>
        </section>

        <section>
            <h3>General Health</h3>
            <p>Wellness and senior exams, ultrasound, x-ray, and dental cleanings are just a few of our general health
                services.</p>
        </section>

        <section>
            <h3>Nutrition</h3>
            <p>Let our nutrition experts review your pet's diet and prescribe a custom nutrition plan for optimum health
                and disease prevention.</p>
        </section>

        <section>
            <h3>Pest Control</h3>
            <p>We offer the latest advances in safe and effective prevention and treatment of fleas, ticks, worms, heart
                worm, and other parasites.</p>
        </section>

        <section>
            <h3>Vaccinations</h3>
            <p>Our veterinarians are experienced in modern vaccination protocols that prevent many of the deadliest
                diseases in pets.</p>
        </section>
    </div><!-- row -->
</div><!-- content container -->

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="webjars/jquery/3.0.0/jquery.min.js"></script>
<script src="webjars/popper.js/1.14.3/popper.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>

</body>
</html>