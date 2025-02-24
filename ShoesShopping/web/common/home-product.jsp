<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section class="product-section spad">
    <div class="container">
        <!-- Filter Controls -->
        <ul class="product-filter controls">
            <li class="control" data-filter=".new">New arrivals</li>
            <li class="control" data-filter="all">Recommended</li>
            <li class="control" data-filter=".best">Best sellers</li>
        </ul>

        <!-- Product List -->
        <div class="row" id="product-filter">
            <c:forEach items="${products}" var="product">
                <div class="mix col-lg-3 col-md-6 best">
                    <div class="product-item">
                        <figure>
                            <img src="template/img/products/1.jpg" alt="template/img/products/1.jpg">
                            <div class="pi-meta">
                                <div class="pi-m-left">
                                    <img src="template/img/icons/eye.png" alt="">
                                    <p>Quick View</p>
                                </div>
                                <div class="pi-m-right">
                                    <img src="template/img/icons/heart.png" alt="">
                                    <p>Save</p>
                                </div>
                            </div>
                        </figure>
                        <div class="product-info text-center">
                            <h6>${product.productName}</h6>
                            <p>$${product.price}</p>
                            <a href="#" class="site-btn btn-line">ADD TO CART</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</section>
