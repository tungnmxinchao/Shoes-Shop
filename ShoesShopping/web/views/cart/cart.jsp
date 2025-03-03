<%-- 
    Document   : cart
    Created on : Mar 3, 2025, 8:19:08 PM
    Author     : TNO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">
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
        css/bootstrap.min.css
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
        <jsp:include page="../../component/header-cart.jsp"></jsp:include>
        <!-- Header section end -->

        <!-- Page -->
        <jsp:include page="../../component/main-cart.jsp"></jsp:include>
        <!-- Page end -->


        <!-- Footer top section -->	
        <jsp:include page="../../common/footer.jsp"></jsp:include>
        <!-- Footer section end -->


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