<%-- 
    Document   : bill
    Created on : Mar 10, 2025, 12:00:00 PM
    Author     : Grok 3 (xAI)
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Bill</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .bill-page {
                padding: 50px 0;
            }
            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 0 15px;
            }
            .bill-table {
                margin-bottom: 30px;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-bottom: 20px;
            }
            th, td {
                padding: 15px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }
            th {
                background-color: #f5f5f5;
            }
            .image-col {
                width: 100px;
                text-align: center;
            }
            .image-col img {
                width: 80px;
                height: 80px;
                object-fit: cover;
            }
            .order-info {
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 5px;
                margin-bottom: 20px;
            }
            .order-info h4 {
                margin-bottom: 15px;
            }
            .order-info p {
                margin: 5px 0;
            }
            .site-btn {
                display: inline-block;
                padding: 12px 25px;
                background-color: #007bff;
                color: white;
                text-decoration: none;
                border-radius: 5px;
                border: none;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            .site-btn:hover {
                background-color: #0056b3;
            }
            .total-col, .price-col {
                text-align: right;
            }
        </style>
    </head>
    <body>
        <div class="bill-page">
            <div class="container">
                <div class="bill-table">
                    <table>
                        <thead>
                            <tr>
                                <th class="image-th">Image</th>
                                <th class="product-th">Product Name</th>
                                <th>Price</th>
                                <th>Quantity</th>
                                <th class="total-th">Total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${cart}" var="cart">
                                <tr>
                                    <td class="image-col">
                                        <img src="../template/img/${cart.product.image != null ? cart.product.image : 'template/img/product/cart.jpg'}" alt="${cart.product.image}">
                                    </td>
                                    <td class="product-col">
                                        <h4>${cart.product.productName}</h4>
                                    </td>
                                    <td class="price-col">
                                        <fmt:formatNumber value="${cart.product.price}" 
                                                          type="number" 
                                                          pattern="#,##0" /> VNĐ
                                    </td>
                                    <td class="quy-col">
                                        ${cart.quantity}
                                    </td>
                                    <td class="total-col">
                                        <fmt:formatNumber value="${cart.product.price * cart.quantity}" 
                                                          type="number" 
                                                          pattern="#,##0" /> VNĐ
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="order-info">
                    <h4>Order Information</h4>
                    <p><strong>Order ID:</strong> ${order.orderId}</p>
                    <p><strong>Order Date:</strong> 
                        <fmt:formatDate value="${order.orderDate}" pattern="dd/MM/yyyy HH:mm:ss" />
                    </p>
                    <p><strong>Total:</strong> 
                        <fmt:formatNumber value="${order.total}" 
                                          type="number" 
                                          pattern="#,##0" /> VNĐ
                    </p>
                    <p><strong>Full Name:</strong> nguyen manh tung</p>
                    <p><strong>Email:</strong> thichsao11@gmail.com</p>
                    <p><strong>Phone:</strong> 0849978689</p>
                </div>

                <div class="payment-button" style="text-align: right;">
                    <form action="createOrder" method="post">
                        <input type="hidden" name="orderId" value="${order.orderId}">                   
                        <button type="submit" class="site-btn">Proceed to Payment</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>