/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TNO
 */
@WebServlet(name = "DeleteCart", urlPatterns = {"/deleteCart"})
public class DeleteCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            CartDAO cartDAO = new CartDAO();
            switch (action) {
                case "clearCart":
                    if (!cartDAO.clearCartByUserId(1)) {
                        throw new Exception("Clear cart failed");
                    }
                    break;
                case "deleteCartId":
                    int cartId = Integer.parseInt(request.getParameter("cartId"));
                    cartDAO.deleteProductFromCart(cartId);
                    break;
                default:
                    throw new AssertionError();
            }

            response.sendRedirect("viewCart");
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
