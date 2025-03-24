<%--
    Document   : manage-order
    Created on : Mar 24, 2025, 10:10:54 AM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Order Management - Shoes Shop</title>
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

        <style>
            .status-pending {
                color: orange;
            }
            .status-completed {
                color: green;
            }
            .status-cancelled {
                color: red;
            }
        </style>
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
                                        <a href="#">Order Management</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Order Management</h4>
                                        </div>

                                        <div class="card-body">
                                            <!-- Error Display Section -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>

                                        <div class="table-responsive">
                                            <table id="basic-datatables" class="display table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Order Date</th>
                                                        <th>Total</th>
                                                        <th>Customer Name</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Order Date</th>
                                                        <th>Total</th>
                                                        <th>Customer Name</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                    <c:forEach items="${orders}" var="order">
                                                        <tr>
                                                            <td>${order.orderId}</td>
                                                            <td><fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                                            <td><fmt:formatNumber value="${order.total}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                                            <td>${order.user.fullName}</td>
                                                            <td>
                                                                <div class="form-button-action">
                                                                    <!-- Edit Button -->
                                                                    <a type="button" href="order-details?orderId=${order.orderId}" class="btn btn-link btn-primary btn-lg" data-bs-toggle="tooltip" title="Edit Order">
                                                                        <i class="fa fa-edit"></i>
                                                                    </a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
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
    <!-- Datatables -->
    <script src="template-admin/assets/js/plugin/datatables/datatables.min.js"></script>
    <!-- Kaiadmin JS -->
    <script src="template-admin/assets/js/kaiadmin.min.js"></script>
    <!-- Kaiadmin DEMO methods, don't include it in your project! -->
    <script src="template-admin/assets/js/setting-demo2.js"></script>
    <script>
            $(document).ready(function () {
                $("#basic-datatables").DataTable({
                    "pageLength": 10, // Number of rows per page
                    "order": [[1, "desc"]] // Sort by Order Date descending by default
                });
            });
    </script>
</body>
</html>