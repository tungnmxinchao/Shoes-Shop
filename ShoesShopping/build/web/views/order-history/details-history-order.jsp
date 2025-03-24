<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="eng">
    <head>
        <title>Order Details</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="template/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="template/css/style.css"/>
    </head>
    <body>

        <!-- Header -->
        <jsp:include page="../../component/header-cart.jsp"></jsp:include>

            <div class="container mt-5">
                <h3>Order Summary</h3>
                <div class="mb-4">
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
        </div>

        <!-- Footer -->
        <jsp:include page="../../common/footer.jsp"></jsp:include>

        <script src="template/js/jquery-3.2.1.min.js"></script>
        <script src="template/js/bootstrap.min.js"></script>
    </body>
</html>
