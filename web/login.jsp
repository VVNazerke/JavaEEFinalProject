<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>

    <%

        String error = request.getParameter("error");
        if (error != null) {

    %>

    <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
        Incorrect <strong>email</strong> or <strong>password</strong>, try again!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%

        }

    %>
    <form method="post" action="/login">
        <div class="row mt-3">
            <div class="col mx-auto">
                <h3 class="text-center">Login on FreshNews.kz</h3>
            </div>
        </div>
        <div class="row mt-3 ">
            <div class="col-6 mx-auto">
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingEmail" placeholder="Password" required
                           name="l-email">
                    <label for="floatingEmail">Email address</label>
                </div>
            </div>
        </div>
        <div class="row mt-3 ">
            <div class="col-6 mx-auto">
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" required
                           name="l-password">
                    <label for="floatingPassword">Password</label>
                </div>
            </div>
        </div>
        <div class="row mt-3 d-flex justify-content-center">
            <div class="col-6">
                <button class="btn btn-outline-warning">Login</button>
            </div>
        </div>
    </form>

    <div class="row">
        <div class="col-6 mx-auto">
            <label>Not yet registered on FreshNews.kz?</label>
            <a href="/sign-up">Sign up</a>
        </div>
    </div>

</div>

</body>
</html>
