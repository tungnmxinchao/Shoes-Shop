<%--
    Document   : order-details
    Created on : Mar 24, 2025, 11:00:00 AM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Order Details - Shoes Shop</title>
        <meta content="width=device-width, initial-scale=1.0, shrink-to-fit=no" name="viewport" />
        <link rel="icon" href="template-admin/assets/img/kaiadmin/favicon.ico" type="image/x-icon" />

        <!-- Fonts and icons -->
        <script src="template-admin/assets/js/plugin/webfont/webfont.min.js"></script>
        <script>
            WebFont.load({
                google: {families: ["Public Sans:300,400,500,600,700"]},
                custom: {
                    families: [
                        "Font Awesome 5 Solid",
                        "Font Awesome 5 Regular",
                        "Font Awesome 5 Brands",
                        "simple-line-icons",
                    ],
                    urls: ["template-admin/assets/css/fonts.min.css"],
                },
                active: function () {
                    sessionStorage.fonts = true;
                },
            });
        </script>

        <!-- CSS Files -->
        <link rel="stylesheet" href="template-admin/assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="template-admin/assets/css/plugins.min.css" />
        <link rel="stylesheet" href="template-admin/assets/css/kaiadmin.min.css" />
        <link rel="stylesheet" href="template-admin/assets/css/demo.css" />
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <jsp:include page="component-dashboard/sildebar-dashboard.jsp"></jsp:include>
                <!-- End Sidebar -->

                <div class="main-panel">
                <jsp:include page="component-dashboard/navbar-header.jsp"></jsp:include>

                    <div class="container">
                        <div class="page-inner">
                            <div class="page-header">
                                <h3 class="fw-bold mb-3">ShoesShop.com</h3>
                                <ul class="breadcrumbs mb-3">
                                    <li class="nav-home">
                                        <a href="#">
                                            <i class="icon-home"></i>
                                        </a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Dashboard</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="manage-order">Order Management</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Order Details</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Order Details - ID: ${order.orderId}</h4>
                                    </div>
                                    <div class="card-body">
                                        <!-- Error Display Section -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>

                                        <!-- Order Summary -->
                                        <div class="mb-4">
                                            <h5>Order Summary</h5>
                                            <table class="table table-bordered">
                                                <tr>
                                                    <th>Order ID</th>
                                                    <td>${order.orderId}</td>
                                                </tr>
                                                <tr>
                                                    <th>Order Date</th>
                                                    <td><fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                                </tr>
                                                <tr>
                                                    <th>Customer Name</th>
                                                    <td>${order.user.fullName}</td>
                                                </tr>
                                                <tr>
                                                    <th>Total</th>
                                                    <td><fmt:formatNumber value="${order.total}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                                </tr>
                                            </table>
                                        </div>

                                        <!-- Order Details Table -->
                                        <h5>Order Items</h5>
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Detail ID</th>
                                                        <th>Product Name</th>
                                                        <th>Image</th>
                                                        <th>Price</th>
                                                        <th>Quantity</th>
                                                        <th>Subtotal</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${orderDetails}" var="detail">
                                                        <tr>
                                                            <td>${detail.detailID}</td>
                                                            <td>${detail.product.productName}</td>
                                                            <td>
                                                                <img style="width: 50px; height: 50px; object-fit: cover;" src="${detail.product.image}" alt="${detail.product.productName}"/>
                                                            </td>
                                                            <td><fmt:formatNumber value="${detail.product.price}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                                            <td>${detail.quantity}</td>
                                                            <td><fmt:formatNumber value="${detail.price}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>

                                        <!-- Back Button -->
                                        <a href="manage-order" class="btn btn-secondary mt-3">Back to Orders</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="component-dashboard/footer-dashboard.jsp"></jsp:include>
        </div>
    </div>

    <!-- Core JS Files -->
    <script src="template-admin/assets/js/core/jquery-3.7.1.min.js"></script>
    <script src="template-admin/assets/js/core/popper.min.js"></script>
    <script src="template-admin/assets/js/core/bootstrap.min.js"></script>

    <!-- jQuery Scrollbar -->
    <script src="template-admin/assets/js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
    <!-- Kaiadmin JS -->
    <script src="template-admin/assets/js/kaiadmin.min.js"></script>
</body>
</html>