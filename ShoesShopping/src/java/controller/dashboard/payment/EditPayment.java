/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard.payment;

import dal.PaymentDAO;
import entity.Payment;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TNO
 */
@WebServlet(name = "EditPayment", urlPatterns = {"/edit-payment"})
public class EditPayment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentIdStr = request.getParameter("paymentId");
        if (paymentIdStr == null || paymentIdStr.isEmpty()) {
            response.sendRedirect("manage-payment");
            return;
        }

        try {
            int paymentId = Integer.parseInt(paymentIdStr);
            PaymentDAO paymentDAO = new PaymentDAO();

            Payment payment = paymentDAO.findPaymentById(paymentId);
            if (payment == null) {
                response.sendRedirect("manage-payment");
                return;
            }

            request.setAttribute("payment", payment);
            request.getRequestDispatcher("views/dashboard/edit-payment.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("manage-payment");
        } catch (Exception e) {
            response.sendRedirect("error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paymentIdStr = request.getParameter("paymentId");
        String paymentStatus = request.getParameter("paymentStatus");

        try {
            int paymentId = Integer.parseInt(paymentIdStr);

            PaymentDAO paymentDAO = new PaymentDAO();

            Payment payment = paymentDAO.findPaymentById(paymentId);

            if (payment != null) {
                payment.setPaymentStatus(paymentStatus);
                if (paymentDAO.updatePayment(payment)) {
                    response.sendRedirect("edit-payment?paymentId=" + paymentId);
                    return;
                }
                throw new Exception("update status failed");
            }
            throw new Exception("Payment not found");

        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp");
        } catch (Exception e) {
            response.sendRedirect("views/error.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
