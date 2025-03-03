/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.CartDAO;
import entity.Cart;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author TNO
 */
public class ViewCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartByUserId(1);

        double totalCart = caculatorCart(cart);

        request.setAttribute("cart", cart);
        request.setAttribute("totalCart", totalCart);
        request.getRequestDispatcher("views/cart/cart.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private double caculatorCart(List<Cart> cart) {
        double totalCart = 0;
        for (Cart cartItem : cart) {
            if (cartItem.getProduct() != null) {
                double price = cartItem.getProduct().getPrice();
                int quantity = cartItem.getQuantity();

                if (price >= 0 && quantity >= 0) {
                    totalCart += quantity * price;
                }
            }
        }
        return totalCart;
    }

}
