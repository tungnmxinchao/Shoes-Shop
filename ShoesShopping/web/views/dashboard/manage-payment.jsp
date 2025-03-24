<%--
    Document   : manage-payment
    Created on : Mar 24, 2025, 12:00:00 PM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Payment Management - Shoes Shop</title>
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
            .status-success {
                color: green;
            }
            .status-pending {
                color: orange;
            }
            .status-failed {
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
                                        <a href="#">Payment Management</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Payment Management</h4>
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
                                                        <th>Payment ID</th>
                                                        <th>Order ID</th>
                                                        <th>Payment Date</th>
                                                        <th>Status</th>
                                                        <th>Transaction ID</th>
                                                        <th>Total Amount</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tfoot>
                                                    <tr>
                                                        <th>Payment ID</th>
                                                        <th>Order ID</th>
                                                        <th>Payment Date</th>
                                                        <th>Status</th>
                                                        <th>Transaction ID</th>
                                                        <th>Total Amount</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </tfoot>
                                                <tbody>
                                                <c:forEach items="${payments}" var="payment">
                                                    <tr>
                                                        <td>${payment.paymentId}</td>
                                                        <td>${payment.orderId}</td>
                                                        <td><fmt:formatDate value="${payment.paymentDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                                    <td>
                                                    <c:choose>
                                                        <c:when test="${payment.paymentStatus == 'Completed'}">
                                                            <span class="status-success">Completed</span>
                                                        </c:when>
                                                        <c:when test="${payment.paymentStatus == 'Pending'}">
                                                            <span class="status-pending">Pending</span>
                                                        </c:when>
                                                        <c:when test="${payment.paymentStatus == 'Failed'}">
                                                            <span class="status-failed">Failed</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${payment.paymentStatus}
                                                        </c:otherwise>
                                                    </c:choose>
                                                    </td>
                                                    <td>${payment.transactionId}</td>
                                                    <td><fmt:formatNumber value="${payment.totalAmount}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                                    <td>
                                                        <div class="form-button-action">
                                                            <!-- Edit Button -->
                                                            <a type="button" href="edit-payment?paymentId=${payment.paymentId}" class="btn btn-link btn-primary btn-lg" data-bs-toggle="tooltip" title="Edit Payment">
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
                    "order": [[2, "desc"]] // Sort by Payment Date descending by default
                });
            });
    </script>
</body>
</html>