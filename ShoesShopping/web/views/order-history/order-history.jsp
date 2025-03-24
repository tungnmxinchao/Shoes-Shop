<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="eng">
    <head>
        <title>Order History</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="template/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="template/css/style.css"/>
    </head>
    <body>

        <!-- Header -->
        <jsp:include page="../../component/header-cart.jsp"></jsp:include>

            <div class="container mt-5">
                <h3>Your Order History</h3>
                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Order Date</th>
                                <th>Customer Name</th>
                                <th>Total</th>
                                <th>Details</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td>${order.orderId}</td>
                                <td><fmt:formatDate value="${order.orderDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                <td>${order.user.fullName}</td>
                                <td><fmt:formatNumber value="${order.total}" type="currency" currencyCode="VND" maxFractionDigits="0"/></td>
                                <td>
                                    <a href="OrderHistoryDetails?orderId=${order.orderId}" class="btn btn-info">View Details</a>
                                </td>
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
