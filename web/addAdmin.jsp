<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <%

        String success = request.getParameter("success");

        if (success != null) {

    %>

    <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">

        Admin added successfully!

        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>

    </div>

    <%

        }

    %>

    <div>
        <form method="post" action="add-admin">
            <div class="row mt-3 mb-3">
                <div class="col">
                    <div class="form-floating">
                        <input type="email" class="form-control" id="floatingEmail" placeholder="Password" required
                               name="aa-email">
                        <label for="floatingEmail">Email address</label>
                    </div>
                </div>
                <div class="col">
                    <div class="form-floating">
                        <input type="text" class="form-control" placeholder="First name" id="floatingFirstName" required
                               name="aa-first-name">
                        <label for="floatingFirstName">First name</label>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <div class="form-floating">
                        <input type="password" class="form-control" id="floatingPassword" placeholder="Password"
                               required name="aa-password">
                        <label for="floatingPassword">Password</label>
                    </div>
                </div>
                <div class="col">
                    <div class="form-floating">
                        <input type="password" class="form-control" id="floatingRePassword"
                               placeholder="Retype password" required name="aa-repassword">
                        <label for="floatingRePassword">Retype password</label>
                    </div>
                </div>
            </div>
            <div class="mt-3">
                <button class="btn btn-outline-warning">Add an admin</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
