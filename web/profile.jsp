<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="head.jsp" %>
</head>
<body>
<div class="container">
    <%@include file="navbar.jsp" %>
    <%

        String error = request.getParameter("password-error");
        if (error != null) {

    %>

    <div class="alert alert-danger alert-dismissible fade show mt-3" role="alert">
        Old password is incorrect!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>

    <%

        }

    %>

    <%
        if (request.getParameter("password-success") != null) {
    %>
    <div class="alert alert-success alert-dismissible fade show mt-3" role="alert">
        Password changed successfully!
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
        }
    %>
    <form method="post" action="/edit-profile">
        <%--        <input type="hidden" value="<%=user.getId()%>" name="user_id">--%>
        <div class="card cd-2 card-body mb-3 mt-3 bg-light">
            <div class="mt-3 row">
                <label for="staticEmail" class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-6">
                    <input type="text" disabled class="form-control" id="staticEmail"
                           value="<%=currentUser.getEmail()%>"
                           name="user_email">
                </div>
            </div>

            <div class="mt-3 row">
                <label for="staticFullName" class="col-sm-2 col-form-label">Full name</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="staticFullName" value="<%=currentUser.getFullName()%>"
                           name="full_name">
                </div>
            </div>
            <div class="mt-3">
                <button class="btn btn-warning btn-sm">Save Changes</button>
            </div>
        </div>
    </form>

    <form method="post" action="/profile">
        <input type="hidden" name="user-email" value="<%=currentUser.getEmail()%>">
        <div class="card cd-2 card-body mb-3 mt-3 bg-light">
            <div class="mt-3 row">
                <label for="staticOldPassword" class="col-sm-2 col-form-label">Old Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="staticOldPassword" name="user_password">
                </div>
            </div>

            <div class="mt-3 row">
                <label for="staticNewPassword" class="col-sm-2 col-form-label">New Password</label>
                <div class="col-sm-6">
                    <input type="password" class="form-control" id="staticNewPassword" name="user_new_password">
                </div>
            </div>
            <div class="mt-3">
                <button class="btn btn-warning btn-sm">Change password</button>
            </div>
        </div>
    </form>

</div>

</body>
</html>
