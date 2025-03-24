/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard.user;

import dal.UserDAO;
import entity.Role;
import entity.Users;
import java.io.IOException;
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
@WebServlet(name = "AddUser", urlPatterns = {"/add-user"})
public class AddUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Validate input parameters
            if (request.getParameter("fullName") == null || request.getParameter("address") == null
                    || request.getParameter("birthday") == null || request.getParameter("phone") == null
                    || request.getParameter("email") == null || request.getParameter("password") == null) {
                throw new IllegalArgumentException("Missing required parameters");
            }

            String fullName = request.getParameter("fullName");
            String address = request.getParameter("address");
            Date birthday = Date.valueOf(request.getParameter("birthday"));
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int roleId = 2;

            Role role = new Role(roleId, "Customer");
            Users user = new Users(0, fullName, password, address, birthday, phone, email, role);

            UserDAO userDAO = new UserDAO();
            boolean isAdded = userDAO.addUser(user);

            if (isAdded) {
                response.sendRedirect("manage-user");
                return;
            } else {
                throw new Exception("Add failed");
            }

        } catch (IllegalArgumentException e) {
            response.sendRedirect("error.jsp");

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
