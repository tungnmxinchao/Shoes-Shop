/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.CartDAO;
import dal.ProductDAO;
import entity.Cart;
import entity.Products;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddToCart extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            
            ProductDAO productDAO = new ProductDAO();
            
            Products product = productDAO.searchProductById(productId);
            
            if(product == null){
                throw new Exception("Product not found");
            }
            
            CartDAO cartDAO = new CartDAO();
            
            //example user lgoin has id = 1
            Cart cart = cartDAO.getCartByUserIdAndProductId(1, productId);
            
            if(cart != null){
                cartDAO.updateCart(cart.getCartID(), cart.getQuantity() + 1);
            }else{
                cartDAO.addToCart(1, productId, 1);
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
