<%@ page import="model.Users" %>
<%
    Users currentUser = (Users) session.getAttribute("currentUser");
%>
<nav class="navbar navbar-expand-lg bg-black navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/"><%=siteName%>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>


        <%
            if (currentUser != null) {
        %>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/">All news</a>
                </li>
                <%
                    if (currentUser.getRoleId() == 1) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="/add-news">Add news</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/add-admin">Add an admin</a>
                </li>
                <%
                    }
                %>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                       aria-expanded="false">
                        <%=currentUser.getFullName()%>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="/profile">Profile</a></li>
                        <%--                        <li><a class="dropdown-item" href="#">Another action</a></li>--%>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li><a class="dropdown-item" href="/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <%
        } else {
        %>
        <div class="collapse navbar-collapse d-flex justify-content-end" id="navbarSupportedContent">
            <div class="navbar-nav me-0 mb-2 mb-lg-0">
                <a class="btn btn-sm btn-outline-warning me-3" href="/login">Login</a>
                <a class="btn btn-sm btn-outline-warning" href="/sign-up">Sign up</a>
            </div>
        </div>
        <%
            }
        %>


    </div>

</nav>