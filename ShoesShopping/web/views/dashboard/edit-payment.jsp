<%--
    Document   : edit-payment
    Created on : Mar 24, 2025, 1:00:00 PM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Edit Payment - Shoes Shop</title>
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
                                        <a href="manage-payment">Payment Management</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Edit Payment</a>
                                    </li>
                                </ul>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Edit Payment - ID: ${payment.paymentId}</h4>
                                    </div>
                                    <div class="card-body">
                                        <!-- Error Display Section -->
                                        <c:if test="${not empty error}">
                                            <div class="alert alert-danger" role="alert">
                                                ${error}
                                            </div>
                                        </c:if>

                                        <form id="editPaymentForm" action="edit-payment" method="post">
                                            <input type="hidden" name="paymentId" value="${payment.paymentId}" />
                                            <div class="form-group">
                                                <label for="orderId">Order ID</label>
                                                <input type="text" class="form-control" id="orderId" name="orderId" value="${payment.orderId}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label for="paymentDate">Payment Date</label>
                                                <input type="text" class="form-control" id="paymentDate" value="<fmt:formatDate value='${payment.paymentDate}' pattern='dd-MM-yyyy HH:mm:ss'/>" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label for="paymentStatus">Payment Status</label>
                                                <select class="form-control" id="paymentStatus" name="paymentStatus" required>
                                                    <option value="Completed" <c:if test="${payment.paymentStatus == 'SUCCESS'}">selected</c:if>>Completed</option>
                                                    <option value="PENDING" <c:if test="${payment.paymentStatus == 'PENDING'}">selected</c:if>>Pending</option>
                                                    <option value="FAILED" <c:if test="${payment.paymentStatus == 'FAILED'}">selected</c:if>>Failed</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="transactionId">Transaction ID</label>
                                                <input type="text" class="form-control" id="transactionId" name="transactionId" value="${payment.transactionId}" readonly>
                                            </div>
                                            <div class="form-group">
                                                <label for="totalAmount">Total Amount</label>
                                                <input type="text" class="form-control" id="totalAmount" value="<fmt:formatNumber value='${payment.totalAmount}' type='currency' currencyCode='VND' maxFractionDigits='0'/>" readonly>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Save Changes</button>
                                            <a href="manage-payment" class="btn btn-secondary">Cancel</a>
                                        </form>
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