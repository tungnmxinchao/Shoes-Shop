/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.order;

import entity.Cart;
import entity.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author TNO
 */
@WebServlet(name = "ViewBill", urlPatterns = {"/viewBill"})
public class ViewBill extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesson = request.getSession();

        Order order = (Order) sesson.getAttribute("order");

        List<Cart> cart = (List<Cart>) sesson.getAttribute("cart");
        
        request.setAttribute("order", order);
        request.setAttribute("cart", cart);
        
        request.getRequestDispatcher("views/bill/bill.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
