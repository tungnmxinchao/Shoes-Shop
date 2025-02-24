<%-- 
    Document   : home
    Created on : Feb 17, 2025, 8:03:54 PM
    Author     : TNO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="eng">
    <head>
        <title>The Plaza - eCommerce Template</title>
        <meta charset="UTF-8">
        <meta name="description" content="The Plaza eCommerce Template">
        <meta name="keywords" content="plaza, eCommerce, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Favicon -->   
        <link href="template/img/favicon.ico" rel="shortcut icon"/>

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Raleway:400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="template/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="template/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="template/css/owl.carousel.css"/>
        <link rel="stylesheet" href="template/css/style.css"/>
        <link rel="stylesheet" href="template/css/animate.css"/>


        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
        <!-- Page Preloder -->
        <div id="preloder">
            <div class="loader"></div>
        </div>

        <!-- Header section -->
        <jsp:include page="../../common/header.jsp"></jsp:include>
            <!-- Header section end -->


            <!-- Hero section -->
        <jsp:include page="../../common/banner.jsp"></jsp:include>
            <!-- Hero section end -->

            <!-- Featured section -->
        <jsp:include page="../../common/feature.jsp"></jsp:include>
            <!-- Featured section end -->


            <!-- Product section -->
        <jsp:include page="../../common/home-product.jsp"></jsp:include>
            <!-- Product section end -->

            <!-- Product section -->
        <jsp:include page="../../component/pagination.jsp"></jsp:include>
            <!-- Product section end -->

            <!-- Footer top section -->	
        <jsp:include page="../../common/footer.jsp"></jsp:include>
        <!-- Footer top section end -->	



        <!--====== Javascripts & Jquery ======-->
        <script src="template/js/jquery-3.2.1.min.js"></script>
        <script src="template/js/bootstrap.min.js"></script>
        <script src="template/js/owl.carousel.min.js"></script>
        <script src="template/js/mixitup.min.js"></script>
        <script src="template/js/sly.min.js"></script>
        <script src="template/js/jquery.nicescroll.min.js"></script>
        <script src="template/js/main.js"></script>
    </body>
</html>
