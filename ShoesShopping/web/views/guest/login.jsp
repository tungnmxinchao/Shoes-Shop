<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
        <title>Login - Shoes Shop</title>
        <link rel="icon" href="template-admin/assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

        <!-- CSS Files -->
        <link rel="stylesheet" href="template-admin/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="template-admin/assets/css/kaiadmin.min.css" />
    </head>
    <body class="login-page">

        <div class="wrapper wrapper-login">
            <div class="container container-login animated fadeIn">
                <div class="login-form">
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title">Login</h4>
                        </div>
                        <div class="card-body">
                            <!-- Display error message if there's any -->
                            <c:if test="${not empty error}">
                                <div class="alert alert-danger">${error}</div>
                            </c:if>

                            <form action="login" method="post">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required placeholder="Enter your email">
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" required placeholder="Enter your password">
                                </div>
                                <button type="submit" class="btn btn-primary btn-block">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Core JS Files -->
        <script src="template-admin/assets/js/core/jquery-3.7.1.min.js"></script>
        <script src="template-admin/assets/js/core/bootstrap.min.js"></script>
    </body>
</html>
