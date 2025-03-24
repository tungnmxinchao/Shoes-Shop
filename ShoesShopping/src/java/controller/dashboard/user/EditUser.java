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
@WebServlet(name = "EditUser", urlPatterns = {"/edit-user"})
public class EditUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int userId = Integer.parseInt(request.getParameter("userId"));

            UserDAO userDAO = new UserDAO();

            Users user = userDAO.findUserById(userId);

            if (user == null) {
                throw new Exception("User not found!");
            }

            request.setAttribute("user", user);

            request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);

        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String userIdStr = request.getParameter("userId");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        String birthdayStr = request.getParameter("birthday");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String roleIdStr = request.getParameter("role");

        try {
            // Validate and parse userId
            int userId = Integer.parseInt(userIdStr);

            UserDAO userDAO = new UserDAO();

            Users existingUser = userDAO.findUserById(userId);
            if (existingUser == null) {
                response.sendRedirect("manage-user");
                return;
            }

            // Validate required fields
            if (fullName == null || fullName.trim().isEmpty()
                    || address == null || address.trim().isEmpty()
                    || birthdayStr == null || birthdayStr.trim().isEmpty()
                    || phone == null || phone.trim().isEmpty()
                    || email == null || email.trim().isEmpty()) {
                request.setAttribute("user", existingUser);
                request.setAttribute("error", "All fields except password are required.");
                request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
                return;
            }

            // Validate email format (basic check)
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                 request.setAttribute("user", existingUser);
                request.setAttribute("error", "Invalid email format.");
                request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
                return;
            }

            // Validate phone (e.g., 10-11 digits)
            if (!phone.matches("\\d{10,11}")) {
                 request.setAttribute("user", existingUser);
                request.setAttribute("error", "Phone number must be 10-11 digits.");
                request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
                return;
            }

            Date birthday = Date.valueOf(birthdayStr);

            int roleId = Integer.parseInt(roleIdStr);
            Role role = new Role();
            role.setRoleId(roleId);

            existingUser.setFullName(fullName.trim());
            existingUser.setAddress(address.trim());
            existingUser.setBirthday(birthday);
            existingUser.setPhone(phone.trim());
            existingUser.setEmail(email.trim());
            existingUser.setRole(role);

            // Update user in the database
            if (userDAO.updateUser(existingUser)) {
                response.sendRedirect("edit-user?userId=" + userId);
                return;
            } else {
                throw new Exception("update failed");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid user ID or role ID.");
            request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format.");
            request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("views/dashboard/edit-user.jsp").forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
