<%-- 
    Document   : main-cart
    Created on : Mar 3, 2025, 8:26:28 PM
    Author     : TNO
--%>
<style>
    /* Căn chỉnh cột Action */
    .action-col {
        text-align: center;
        vertical-align: middle;
        width: 100px; /* Đặt chiều rộng cố định cho cột nếu cần */
    }

    /* Định dạng link Delete với icon */
    .delete-link {
        display: inline-flex;
        align-items: center;
        justify-content: center;
        color: #e74c3c; /* Màu đỏ nhạt cho icon */
        text-decoration: none;
        font-size: 1.2rem; /* Kích thước icon */
        padding: 5px;
        transition: color 0.3s ease;
    }

    .delete-link:hover {
        color: #c0392b; /* Màu đỏ đậm hơn khi hover */
    }

    /* Responsive cho màn hình nhỏ */
    @media (max-width: 768px) {
        .action-col {
            width: 80px; /* Giảm chiều rộng trên màn hình nhỏ */
        }

        .delete-link {
            font-size: 1rem; /* Giảm kích thước icon */
            padding: 3px;
        }
    }

    @media (max-width: 576px) {
        .action-col {
            width: 60px;
        }

        .delete-link {
            font-size: 0.9rem;
        }
    }
</style>
<script>
    function submitUpdateCart() {
        // Lấy tất cả các input quantity
        const quantityInputs = document.querySelectorAll('input[name="quantity"]');

        // Tạo mảng để chứa cartId và quantity
        let cartIds = [];
        let quantities = [];

        // Duyệt qua từng input để lấy dữ liệu
        quantityInputs.forEach(input => {
            const cartId = input.getAttribute('data-cart-id'); // Lấy cartId từ attribute
            const quantity = input.value; // Lấy quantity từ giá trị input

            if (cartId && quantity) {
                cartIds.push(cartId);
                quantities.push(quantity);
            }
        });

        // Chuyển mảng thành chuỗi, cách nhau bằng dấu phẩy
        const arrayCartId = cartIds.join(',');
        const arrayQuantity = quantities.join(',');

        // Gán giá trị vào các input ẩn
        document.getElementById('arrayCartId').value = arrayCartId;
        document.getElementById('arrayQuantity').value = arrayQuantity;

        // Submit form
        document.getElementById('updateCartForm').submit();
    }
</script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="page-area cart-page spad">
    <div class="container">
        <div class="cart-table">
            <!-- Thêm form bao quanh bảng -->
            <form id="updateCartForm" action="updateCart" method="post">
                <table>
                    <thead>
                        <tr>
                            <th class="product-th">Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th class="total-th">Total</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${cart}" var="cart">
                            <tr>
                                <td class="product-col">
                                    <img src="template/img/product/cart.jpg" alt="">
                                    <div class="pc-title">
                                        <h4>${cart.product.productName}</h4>
                                        <a href="#">Edit Product</a>
                                    </div>
                                </td>
                                <td class="price-col">
                                    <fmt:formatNumber value="${cart.product.price}" 
                                                      type="number" 
                                                      pattern="#,##0" /> VNĐ
                                </td>
                                <td class="quy-col">
                                    <div class="quy-input">
                                        <span>Qty</span>
                                        <!-- Thêm data-cart-id để lưu cartId và đặt name cho input -->
                                        <input type="number" 
                                               name="quantity" 
                                               value="${cart.quantity}" 
                                               data-cart-id="${cart.cartID}" 
                                               min="1">
                                    </div>
                                </td>
                                <td class="total-col">
                                    <fmt:formatNumber value="${cart.product.price * cart.quantity}" 
                                                      type="number" 
                                                      pattern="#,##0" /> VNĐ
                                </td>
                                <td class="action-col">
                                    <a href="deleteCart?cartId=${cart.cartID}&action=deleteCartId" 
                                       class="delete-link" 
                                       onclick="return confirm('Are you sure you want to delete this item?')">
                                        <i class="fas fa-trash-alt"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <input type="hidden" name="arrayCartId" id="arrayCartId">
                <input type="hidden" name="arrayQuantity" id="arrayQuantity">
            </form>
        </div>
        <div class="row cart-buttons">
            <div class="col-lg-5 col-md-5">
                <a href="home" class="site-btn btn-continue">Continue shopping</a>
            </div>
            <div class="col-lg-7 col-md-7 text-lg-right text-left">
                <a href="deleteCart?action=clearCart" class="site-btn btn-clear">Clear cart</a>

                <div class="site-btn btn-line btn-update" onclick="submitUpdateCart()">Update Cart</div>
            </div>
        </div>
    </div>
    <div class="card-warp">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="shipping-info">
                        <h4>Shipping method</h4>
                        <p>Select the one you want</p>
                        <div class="shipping-chooes">
                            <div class="sc-item">
                                <input type="radio" name="sc" id="one">
                                <label for="one">Next day delivery<span>$4.99</span></label>
                            </div>
                            <div class="sc-item">
                                <input type="radio" name="sc" id="two">
                                <label for="two">Standard delivery<span>$1.99</span></label>
                            </div>
                            <div class="sc-item">
                                <input type="radio" name="sc" id="three">
                                <label for="three">Personal Pickup<span>Free</span></label>
                            </div>
                        </div>
                        <h4>Cupon code</h4>
                        <p>Enter your cupone code</p>
                        <div class="cupon-input">
                            <input type="text">
                            <button class="site-btn">Apply</button>
                        </div>
                    </div>
                </div>
                <div class="offset-lg-2 col-lg-6">
                    <div class="cart-total-details">
                        <h4>Cart total</h4>
                        <p>Final Info</p>
                        <ul class="cart-total-card">
                            <li>
                                Subtotal
                                <span>
                                    <fmt:formatNumber value="${totalCart}" 
                                                      type="number" 
                                                      pattern="#,##0" /> VNĐ
                                </span>
                            </li>
                            <li>Shipping<span>Free</span></li>
                            <li class="total">Total<span>  <fmt:formatNumber value="${totalCart}" 
                                              type="number" 
                                              pattern="#,##0" /> VNĐ</span></li>
                        </ul>
                        <a class="site-btn btn-full" href="checkout.html">Proceed to checkout</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

