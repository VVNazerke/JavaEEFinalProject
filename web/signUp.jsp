<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <div class="mt-3">
        <h4 class="text-center">Quick registration on FreshNews.kz</h4>
    </div>
    <%

        String emailError = request.getParameter("email-error");
        if (emailError != null) {

    %>

    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        An account with this email already exists!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%

        }

    %>

    <%

        String passwordError = request.getParameter("password-error");
        if (passwordError != null) {

    %>

    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        Passwords don't match!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%

        }

    %>

    <%

        String success = request.getParameter("success");

        if (success != null) {

    %>

    <div class="alert alert-success alert-dismissible fade show" role="alert">

        User added successfully!

        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>

    </div>

    <%

        }

    %>
    <form method="post" action="sign-up">
        <div class="row mt-3 mb-3">
            <div class="col">
                <div class="form-floating">
                    <input type="email" class="form-control" id="floatingEmail" placeholder="Password" required
                           name="su-email">
                    <label for="floatingEmail">Email address</label>
                </div>
            </div>
            <div class="col">
                <div class="form-floating">
                    <input type="text" class="form-control" placeholder="First name" id="floatingFirstName" required
                           name="su-first-name">
                    <label for="floatingFirstName">First name</label>
                </div>
            </div>
            <div class="col">
                <div class="form-floating">
                    <input type="text" class="form-control" placeholder="Second name" id="floatingSecondName" required
                           name="su-second-name">
                    <label for="floatingSecondName">Second name</label>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col">
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="Password" required
                           name="su-password">
                    <label for="floatingPassword">Password</label>
                </div>
            </div>
            <div class="col">
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingRePassword" placeholder="Retype password"
                           required name="su-repassword">
                    <label for="floatingRePassword">Retype password</label>
                </div>
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-outline-warning">Sign Up</button>
        </div>
    </form>

    <div class="row">
        <div class="col-6">
            <label>Already have an account?</label>
            <a href="/login">Login</a>
        </div>
    </div>

</div>

</body>
</html>
