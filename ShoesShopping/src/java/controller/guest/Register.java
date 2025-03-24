/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.guest;

import dal.UserDAO;
import entity.Role;
import entity.Users;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;

/**
 *
 * @author TNO
 */
@WebServlet(name = "Register", urlPatterns = {"/register"})
public class Register extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("views/guest/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String birthday = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Date date = Date.valueOf(birthday);

        Role role = new Role(2, "");

        Users user = new Users(0, fullName, email, address, date, phone, email, role);

        UserDAO userDAO = new UserDAO();

        if (userDAO.addUser(user)) {
            response.sendRedirect("login");
        } else {
            response.sendRedirect("register");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
