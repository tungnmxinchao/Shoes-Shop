/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Cart;
import entity.Order;
import entity.Payment;
import java.sql.*;
import java.util.List;

public class OrderDAO extends DBContext {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;

    public OrderDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
    }

    public int insertOrder(Order order, List<Cart> cart) {

        String sqlInsertOrder = "INSERT INTO [dbo].[tblOrder] ([total], [userID])  \n"
                + "VALUES (?, ?)";

        String sqlOrderDetails = "INSERT INTO [dbo].[tblOrderDetail] ([price], [quantity], [orderID], [productID])  \n"
                + "VALUES (?, ?, ?, ?)";
        int orderId = 0;
        try {
            ps = conn.prepareStatement(sqlInsertOrder, ps.RETURN_GENERATED_KEYS);

            ps.setDouble(1, order.getTotal());
            ps.setInt(2, order.getUser().getUserId());

            int rowAff = ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);

                    for (int i = 0; i < cart.size(); i++) {
                        ps = conn.prepareStatement(sqlOrderDetails);
                        double priceProduct = cart.get(i).getProduct().getPrice();

                        ps.setDouble(1, priceProduct * cart.get(i).getQuantity());
                        ps.setInt(2, cart.get(i).getQuantity());
                        ps.setInt(3, orderId);
                        ps.setInt(4, cart.get(i).getProduct().getProductID());

                        int rowAffOrderDetails = ps.executeUpdate();

                        if (rowAffOrderDetails < 0) {
                            return -1;
                        }

                    }

                }
            }

            if (rowAff > 0) {
                return orderId;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    public boolean insertPayment(Payment payment) {
        String sql = "INSERT INTO [dbo].[tblPayment] ([orderID], [paymentStatus], [transactionID], [totalAmount]) \n"
                + "VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, payment.getOrderId());
            ps.setString(2, payment.getPaymentStatus());
            ps.setString(3, payment.getTransactionId());
            ps.setDouble(4, payment.getTotalAmount());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 

        } catch (SQLException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
        }
        return false; 
    }
}
