/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import dal.CartDAO;
import dal.OrderDAO;
import entity.Cart;
import entity.Order;
import entity.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import utils.CartUtils;
import java.sql.Timestamp;

/**
 *
 * @author TNO
 */
@WebServlet(name = "InsertOrder", urlPatterns = {"/insertOrder"})
public class InsertOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesson = request.getSession();

        Users user = (Users) sesson.getAttribute("user");

        int userId = user.getUserId();

        OrderDAO orderDAO = new OrderDAO();
        CartDAO cartDAO = new CartDAO();

        List<Cart> cart = cartDAO.getCartByUserId(userId);

        double total = CartUtils.caculatorCart(cart);

        Order order = new Order(0, null, total, user);

        int orderId = orderDAO.insertOrder(order, cart);

        if (orderId != -1) {

            order.setOrderId(orderId);
            order.setOrderDate(new Timestamp(System.currentTimeMillis()));

            sesson.setAttribute("order", order);
            sesson.setAttribute("cart", cart);

            response.sendRedirect("viewBill");
        } else {
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
