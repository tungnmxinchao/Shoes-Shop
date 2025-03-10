<%-- 
    Document   : error
    Created on : Mar 10, 2025, 8:16:39 PM
    Author     : TNO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Lỗi - Error</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                background-color: #f8d7da;
                text-align: center;
            }
            .error-container {
                background: white;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }
            .error-code {
                font-size: 80px;
                font-weight: bold;
                color: #dc3545;
            }
            .error-message {
                font-size: 20px;
                margin: 15px 0;
            }
        </style>
    </head>
    <body>

        <div class="error-container">
            <div class="error-code">404</div>
            <h2 class="error-message">Xin lỗi! Có lỗi xảy ra.</h2>
            <p>Trang bạn yêu cầu không tồn tại hoặc đã bị xóa.</p>
            <a href="index.jsp" class="btn btn-danger">Quay về trang chủ</a>
        </div>

    </body>
</html>
