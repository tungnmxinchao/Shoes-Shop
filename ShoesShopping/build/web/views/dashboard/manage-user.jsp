<%--
    Document   : manage-user
    Created on : Mar 24, 2025, 10:10:54 AM
    Author     : TNO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Shoes Shop</title>
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
        <style>
            .status-active {
                color: green;
            }

            .status-blocked {
                color: red;
            }
        </style>
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
                                        <a href="#">User Management</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="card">
                                        <div class="card-header">
                                            <h4 class="card-title">User Management</h4>
                                        </div>

                                        <div class="card-body">
                                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addUserModal">
                                                Add User
                                            </button>

                                            <div class="table-responsive">
                                                <table
                                                    id="basic-datatables"
                                                    class="display table table-striped table-hover"
                                                    >
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Full Name</th>
                                                            <th>Address</th>
                                                            <th>Birthday</th>
                                                            <th>Phone</th>
                                                            <th>Email</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tfoot>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Full Name</th>
                                                            <th>Address</th>
                                                            <th>Birthday</th>
                                                            <th>Phone</th>
                                                            <th>Email</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </tfoot>
                                                    <tbody>
                                                    <c:forEach items="${users}" var="user">
                                                        <tr>
                                                            <td>${user.userId}</td>
                                                            <td>${user.fullName}</td>
                                                            <td>${user.address}</td>
                                                            <td>${user.birthday}</td>
                                                            <td>${user.phone}</td>
                                                            <td>${user.email}</td>
                                                            <td>
                                                                <div class="form-button-action">
                                                                    <!-- Edit Button -->
                                                                    <a type="button" href="edit-user?userId=${user.userId}" class="btn btn-link btn-primary btn-lg" data-bs-toggle="tooltip" title="Edit User">
                                                                        <i class="fa fa-edit"></i>
                                                                    </a>
                                                                    <!-- Delete Button -->
                                                                    <a type="button" href="delete-user?userId=${user.userId}" class="btn btn-link btn-danger" data-bs-toggle="tooltip" title="Remove">
                                                                        <i class="fa fa-times"></i>
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

                <jsp:include page="component-dashboard/footer-dashboard.jsp"></jsp:include>
                </div>
            </div>
            <!-- Modal for Adding User (Optional, can be created separately) -->
        <jsp:include page="component-dashboard/modal-add-user.jsp"></jsp:include>
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
            <script>
            $(document).ready(function () {
                $("#basic-datatables").DataTable({});

                $("#multi-filter-select").DataTable({
                    pageLength: 5,
                    initComplete: function () {
                        this.api()
                                .columns()
                                .every(function () {
                                    var column = this;
                                    var select = $(
                                            '<select class="form-select"><option value=""></option></select>'
                                            )
                                            .appendTo($(column.footer()).empty())
                                            .on("change", function () {
                                                var val = $.fn.dataTable.util.escapeRegex($(this).val());

                                                column
                                                        .search(val ? "^" + val + "$" : "", true, false)
                                                        .draw();
                                            });

                                    column
                                            .data()
                                            .unique()
                                            .sort()
                                            .each(function (d, j) {
                                                select.append(
                                                        '<option value="' + d + '">' + d + "</option>"
                                                        );
                                            });
                                });
                    },
                });

                // Add Row
                $("#add-row").DataTable({
                    pageLength: 5,
                });

                var action =
                        '<td> <div class="form-button-action"> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-primary btn-lg" data-original-title="Edit User"> <i class="fa fa-edit"></i> </button> <button type="button" data-bs-toggle="tooltip" title="" class="btn btn-link btn-danger" data-original-title="Remove"> <i class="fa fa-times"></i> </button> </div> </td>';

                $("#addRowButton").click(function () {
                    $("#add-row")
                            .dataTable()
                            .fnAddData([
                                $("#addUsername").val(),
                                $("#addEmail").val(),
                                $("#addFullName").val(),
                                action,
                            ]);
                    $("#addRowModal").modal("hide");
                });
            });


        </script>
    </body>
</html>