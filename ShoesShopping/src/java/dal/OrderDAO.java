/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Cart;
import entity.Order;
import entity.Payment;
import entity.Role;
import entity.Users;
import java.sql.*;
import java.util.List;
import java.util.Vector;

public class OrderDAO extends DBContext {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;

    private Vector<Order> orders;

    public OrderDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
        orders = new Vector<>();
    }

    public Vector<Order> findAllOrder() {
        try {
            String query = "SELECT o.orderID, o.orderDate, o.total, u.userID, u.fullName, u.password, u.address, u.birthday, u.phone, u.email, r.roleID, r.roleName "
                    + "FROM tblOrder o "
                    + "JOIN tblUsers u ON o.userID = u.userID "
                    + "JOIN tblRoles r ON u.roleID = r.roleID";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("roleName"));
                Users user = new Users(rs.getInt("userID"), rs.getString("fullName"), rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"), rs.getString("email"), role);
                Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("orderDate"), rs.getDouble("total"), user);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Vector<Order> findAllOrderByUserId(int userId) {
        Vector<Order> orders = new Vector<>();
        try {
            String query = "SELECT o.orderID, o.orderDate, o.total, u.userID, u.fullName, u.password, u.address, u.birthday, u.phone, u.email, r.roleID, r.roleName "
                    + "FROM tblOrder o "
                    + "JOIN tblUsers u ON o.userID = u.userID "
                    + "JOIN tblRoles r ON u.roleID = r.roleID "
                    + "WHERE o.userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);  // Thiết lập giá trị userID vào câu truy vấn
            rs = ps.executeQuery();
            while (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("roleName"));
                Users user = new Users(rs.getInt("userID"), rs.getString("fullName"), rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"), rs.getString("email"), role);
                Order order = new Order(rs.getInt("orderID"), rs.getTimestamp("orderDate"), rs.getDouble("total"), user);
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Order findOrderById(int id) {
        Order order = null;
        try {
            String query = "SELECT o.orderID, o.orderDate, o.total, u.userID, u.fullName, u.password, u.address, u.birthday, u.phone, u.email, r.roleID, r.roleName "
                    + "FROM tblOrder o "
                    + "JOIN tblUsers u ON o.userID = u.userID "
                    + "JOIN tblRoles r ON u.roleID = r.roleID "
                    + "WHERE o.orderID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Role role = new Role(rs.getInt("roleID"), rs.getString("roleName"));
                Users user = new Users(rs.getInt("userID"), rs.getString("fullName"), rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"), rs.getString("email"), role);
                order = new Order(rs.getInt("orderID"), rs.getTimestamp("orderDate"), rs.getDouble("total"), user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public boolean updateOrder(Order order) {
        try {
            String query = "UPDATE tblOrder SET orderDate = ?, total = ?, userID = ? WHERE orderID = ?";
            ps = conn.prepareStatement(query);
            ps.setTimestamp(1, order.getOrderDate());
            ps.setDouble(2, order.getTotal());
            ps.setInt(3, order.getUser().getUserId());
            ps.setInt(4, order.getOrderId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
