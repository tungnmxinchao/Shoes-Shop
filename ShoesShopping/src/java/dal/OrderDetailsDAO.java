/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import entity.OrderDetails;
import entity.Products;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author TNO
 */
public class OrderDetailsDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;

    private Vector<OrderDetails> orderdetails;

    public OrderDetailsDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
        orderdetails = new Vector<>();
    }

    public static void main(String[] args) {
        OrderDetailsDAO odao = new OrderDetailsDAO();
        Vector<OrderDetails> oo = odao.findAllOrderDetailsByOrderId(1);
        for (OrderDetails orderDetails : oo) {
            System.out.println(orderDetails);
        }
    }

    public Vector<OrderDetails> findAllOrderDetailsByOrderId(int id) {
        try {
            String query = "select * from tblOrderDetail o\n"
                    + "join tblProduct p \n"
                    + "on o.productID = p.productID\n"
                    + "where o.orderID = ?";

            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Products product = new Products(rs.getInt("productID"),
                        rs.getString("productName"), rs.getString("image"),
                        rs.getDouble(9));
                
                 OrderDetails orderDetail = new OrderDetails(rs.getInt("detailID"), 
                         rs.getDouble("price"), rs.getInt("quantity"), null, product);
                 
                 orderdetails.add(orderDetail);
        
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderdetails;
    }
}
