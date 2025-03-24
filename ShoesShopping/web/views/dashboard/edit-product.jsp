<%-- 
    Document   : edit-product
    Created on : Mar 24, 2025, 10:10:54 AM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Edit Product - Kaiadmin Bootstrap 5 Admin Dashboard</title>
        <meta
            content="width=device-width, initial-scale=1.0, shrink-to-fit=no"
            name="viewport"
            />
        <link
            rel="icon"
            href="template-admin/assets/img/kaiadmin/favicon.ico"
            type="image/x-icon"
            />

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

        <!-- CSS Just for demo purpose, don't include it in your project -->
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
                                <h3 class="fw-bold mb-3">Edit Product</h3>
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
                                        <a href="manage-product">Products</a>
                                    </li>
                                    <li class="separator">
                                        <i class="icon-arrow-right"></i>
                                    </li>
                                    <li class="nav-item">
                                        <a href="#">Edit Product</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">Edit Product</h4>
                                        </div>
                                        <div class="card-body">
                                        <c:if test="${not empty successMessage}">
                                            <div class="alert alert-success">
                                                ${successMessage}
                                            </div>
                                        </c:if>
                                        <c:if test="${not empty errorMessage}">
                                            <div class="alert alert-danger">
                                                ${errorMessage}
                                            </div>
                                        </c:if>
                                        <form action="edit-product" method="post" enctype="multipart/form-data">
                                            <input type="hidden" name="productID" value="${product.productID}">
                                            <div class="form-group">
                                                <label for="productName">Product Name</label>
                                                <input type="text" class="form-control" id="productName" name="productName" value="${product.productName}" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="image">Image</label>
                                                <input type="file" class="form-control" id="image" name="image">
                                                <img src="${product.image}" alt="${product.productName}" style="width: 100px; height: 100px; object-fit: cover;">
                                            </div>
                                            <div class="form-group">
                                                <label for="price">Price</label>
                                                <input type="number" class="form-control" id="price" name="price" value="${product.price}" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="quantity">Quantity</label>
                                                <input type="number" class="form-control" id="quantity" name="quantity" value="${product.quantity}" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="category">Category</label>
                                                <select class="form-control" id="category" name="categoryID" required>
                                                    <c:forEach items="${categories}" var="category">
                                                        <option value="${category.categoryId}" ${product.category.categoryId == category.categoryId ? 'selected' : ''}>${category.categoryName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="importDate">Import Date</label>
                                                <input type="date" class="form-control" id="importDate" name="importDate" value="${product.importDate}" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="usingDate">Using Date</label>
                                                <input type="date" class="form-control" id="usingDate" name="usingDate" value="${product.usingDate}" required>
                                            </div>
                                            <div class="form-group">
                                                <label for="status">Status</label>
                                                <select class="form-control" id="status" name="status" required>
                                                    <option value="1" ${product.status == 1 ? 'selected' : ''}>Active</option>
                                                    <option value="0" ${product.status == 0 ? 'selected' : ''}>Blocked</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Update Product</button>
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
    <!--   Core JS Files   -->
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
</body>
</html>