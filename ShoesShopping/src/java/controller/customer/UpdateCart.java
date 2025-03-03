/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.CartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TNO
 */
public class UpdateCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String cartId = request.getParameter("arrayCartId");    // "1,2,3,4,5"
            String quantity = request.getParameter("arrayQuantity"); // "2,5,1,2,3"

            CartDAO cartDAO = new CartDAO();

            if (cartId != null && quantity != null && !cartId.isEmpty() && !quantity.isEmpty()) {
                String[] cartIdArray = cartId.split(",");
                String[] quantityArray = quantity.split(",");

                if (cartIdArray.length == quantityArray.length) {
                    for (int i = 0; i < cartIdArray.length; i++) {
                        try {

                            int id = Integer.parseInt(cartIdArray[i].trim());
                            int qty = Integer.parseInt(quantityArray[i].trim());

                            cartDAO.updateCart(id, qty);
                        } catch (NumberFormatException e) {
                            response.sendRedirect("error.jsp");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Số lượng cartId và quantity không khớp");
                }
            } else {
                throw new IllegalArgumentException("Dữ liệu đầu vào bị thiếu hoặc rỗng");
            }
            response.sendRedirect("viewCart");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.sendRedirect("error.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
