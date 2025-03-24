<%-- 
    Document   : header
    Created on : Feb 17, 2025, 8:16:24 PM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header class="header-section">
    <div class="container-fluid">
        <!-- logo -->
        <div class="site-logo">
            <img src="template/img/logo.png" alt="logo">
        </div>
        <!-- responsive -->
        <div class="nav-switch">
            <i class="fa fa-bars"></i>
        </div>
        <div class="header-right">
            <a href="viewCart" class="card-bag"><img src="template/img/icons/bag.png" alt=""><span>2</span></a>
            <a href="#" class="search"><img src="template/img/icons/search.png" alt=""></a>
        </div>
        <!-- site menu -->
        <ul class="main-menu">
            <li><a href="home">Home</a></li>
                <c:if test="${sessionScope.user.role.roleId == 2}">
                <li><a href="order-history">Order History</a></li>
                </c:if>
                <c:if test="${sessionScope.user.role.roleId == 1}">
                <li><a href="manage-product">Dashboard</a></li>
                </c:if>
                <c:if test="${sessionScope.user == null}">
                <li><a href="login">Login</a></li>
                <li><a href="contact.html">Register</a></li>
                </c:if>

            <c:if test="${sessionScope.user != null}">
                <li><a>Hello ${sessionScope.user.fullName}</a></li>
                <li><a href="logout">Logout</a></li>
                </c:if>
        </ul>
    </div>
</header>