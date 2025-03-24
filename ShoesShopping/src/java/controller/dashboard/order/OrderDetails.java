/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard.order;

import dal.OrderDAO;
import dal.OrderDetailsDAO;
import entity.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author TNO
 */
@WebServlet(name = "OrderDetails", urlPatterns = {"/order-details"})
public class OrderDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderIdStr = request.getParameter("orderId");
        if (orderIdStr == null || orderIdStr.isEmpty()) {
            response.sendRedirect("error.jsp");
            return;
        }

        try {
            int orderId = Integer.parseInt(orderIdStr);

            OrderDAO orderDAO = new OrderDAO();
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();

            Order order = orderDAO.findOrderById(orderId);
            if (order == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            Vector<entity.OrderDetails> orderDetails = orderDetailsDAO.findAllOrderDetailsByOrderId(orderId);

            request.setAttribute("order", order);
            request.setAttribute("orderDetails", orderDetails);

            request.getRequestDispatcher("views/dashboard/order-details.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
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
