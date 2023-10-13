<%@ page import="java.util.List" %>
<%@ page import="kz.bitlab.model.Item" %>
<%@ page import="kz.bitlab.model.City" %><%--
  Created by IntelliJ IDEA.
  User: Kuat
  Date: 06.10.2023
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="col-6 mx-auto">
    <%
        String emailError = request.getParameter("emailError");
        if (emailError != null) {
    %>
    <h1 class="text-danger">INCORRECT EMAIL!</h1>
    <%
        }
    %>
    <%
        String passwordError = request.getParameter("passwordError");
        if (passwordError != null) {
    %>
    <h1 class="text-danger">INCORRECT PASSWORD!</h1>
    <%
        }
    %>

    <%
        String regErrorEmail = request.getParameter("regErrorEmail");
        if (regErrorEmail != null) {
    %>
    <h1 class="text-danger">Email is busy!</h1>
    <%
        }
    %>
    <%
        String regErrorPasswords = request.getParameter("regErrorPasswords");
        if (regErrorPasswords != null) {
    %>
    <h1 class="text-danger">Passwords are not same!</h1>
    <%
        }
    %>
    <%
        String success = request.getParameter("success");
        if (success != null) {
    %>
    <h1 class="text-success">Account created successfully!</h1>
    <%
        }
    %>
    <form action="/sign-in" method="post">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input name="user_email" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input name="user_password" type="password" class="form-control" id="exampleInputPassword1">
        </div>
        <button type="submit" class="btn btn-primary">SIGN IN</button>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#signUpModal">
            SIGN UP
        </button>
    </form>

    <!-- Modal -->
    <form action="/sign-up" method="post">
    <div class="modal fade" id="signUpModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">SIGN UP</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="exampleInputEmail1" class="form-label">Email address</label>
                        <input name="user_email" type="email" class="form-control" id="exampleInputEmail12" aria-describedby="emailHelp">
                        <div id="emailHelp2" class="form-text">We'll never share your email with anyone else.</div>
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword12" class="form-label">Password</label>
                        <input name="user_password" type="password" class="form-control" id="exampleInputPassword12">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputPassword13" class="form-label">Repeat Password</label>
                        <input name="user_re_password" type="password" class="form-control" id="exampleInputPassword13">
                    </div>
                    <div class="mb-3">
                        <label for="exampleInputFullName" class="form-label">Full name</label>
                        <input name="user_full_name" type="text" class="form-control" id="exampleInputFullName">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="submit" class="btn btn-primary">CREATE NEW ACCOUNT</button>
                </div>
            </div>
        </div>
    </div>
    </form>
</div>
</body>
</html>
