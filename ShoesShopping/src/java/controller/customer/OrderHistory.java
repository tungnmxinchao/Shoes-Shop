/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.OrderDAO;
import entity.Order;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Vector;

/**
 *
 * @author TNO
 */
@WebServlet(name = "OrderHistory", urlPatterns = {"/order-history"})
public class OrderHistory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesson = request.getSession();

        Users user = (Users) sesson.getAttribute("user");

        int userId = user.getUserId();
        OrderDAO orderDAO = new OrderDAO();

        Vector<Order> orders = orderDAO.findAllOrderByUserId(user.getUserId());
        request.setAttribute("orders", orders);

        request.getRequestDispatcher("views/order-history/order-history.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
