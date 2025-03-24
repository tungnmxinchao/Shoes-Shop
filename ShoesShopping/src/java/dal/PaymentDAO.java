/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Payment;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author TNO
 */
public class PaymentDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;

    private Vector<Payment> payments;

    public PaymentDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
        payments = new Vector<>();
    }

    public Vector<Payment> findAllPayments() {
        try {
            String query = "SELECT * FROM tblPayment";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Payment payment = new Payment(rs.getInt("paymentID"), rs.getInt("orderID"),
                        rs.getTimestamp("paymentDate"), rs.getString("paymentStatus"),
                        rs.getString("transactionID"), rs.getDouble("totalAmount"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }

    public boolean updatePayment(Payment payment) {
        try {
            String query = "UPDATE tblPayment SET orderID = ?, paymentDate = ?, paymentStatus = ?, transactionID = ?, totalAmount = ? WHERE paymentID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, payment.getOrderId());
            ps.setTimestamp(2, payment.getPaymentDate());
            ps.setString(3, payment.getPaymentStatus());
            ps.setString(4, payment.getTransactionId());
            ps.setDouble(5, payment.getTotalAmount());
            ps.setInt(6, payment.getPaymentId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
