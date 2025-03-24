<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng Ký Người Dùng</title>
        <link rel="stylesheet" href="styles.css">

        <style>
            /* Đặt font mặc định */
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            }

            h2 {
                text-align: center;
                color: #333;
                font-size: 24px;
            }

            /* Center form */
            .form-container {
                background-color: #fff;
                max-width: 500px;
                margin: 50px auto;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
            }

            /* Các nhóm form */
            .form-group {
                margin-bottom: 15px;
            }

            label {
                display: block;
                font-weight: bold;
                margin-bottom: 5px;
                color: #555;
            }

            input[type="text"],
            input[type="password"],
            input[type="date"],
            input[type="email"] {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 16px;
                box-sizing: border-box;
            }

            input[type="submit"] {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                font-size: 18px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            input:focus {
                border-color: #4CAF50;
                outline: none;
            }

            input[type="text"]:invalid,
            input[type="email"]:invalid {
                border-color: red;
            }

            input[type="text"]:valid,
            input[type="email"]:valid {
                border-color: green;
            }

        </style>
    </head>
    <body>
        <div class="form-container">
            <h2>Đăng Ký Người Dùng</h2>
            <form action="register" method="POST">
                <div class="form-group">
                    <label for="fullName">Họ Tên:</label>
                    <input type="text" id="fullName" name="fullName" required>
                </div>

                <div class="form-group">
                    <label for="password">Mật Khẩu:</label>
                    <input type="password" id="password" name="password" required>
                </div>

                <div class="form-group">
                    <label for="address">Địa Chỉ:</label>
                    <input type="text" id="address" name="address" required>
                </div>

                <div class="form-group">
                    <label for="birthday">Ngày Sinh:</label>
                    <input type="date" id="birthday" name="birthday" required>
                </div>

                <div class="form-group">
                    <label for="phone">Số Điện Thoại:</label>
                    <input type="text" id="phone" name="phone" required>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <input type="submit" value="Đăng Ký">
                </div>
            </form>
        </div>
    </body>
</html>
