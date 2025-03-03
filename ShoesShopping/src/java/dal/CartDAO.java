/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Cart;
import entity.Products;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO extends DBContext {

    private PreparedStatement ps;
    private ResultSet rs;

    public static void main(String[] args) {
        CartDAO cartDAO = new CartDAO();
        List<Cart> cart = cartDAO.getCartByUserId(1);
        for (Cart o : cart) {
            System.out.println(o);
        }
    }

    public Cart getCartByUserIdAndProductId(int userId, int productId) {
        String sql = "select * from tblCart c\n"
                + "join tblProduct p\n"
                + "on c.productID = p.productID\n"
                + "where c.productId = ? and c.userId = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            ps.setInt(1, productId);
            ps.setInt(2, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int cartID = rs.getInt("cartID");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                String productImg = rs.getString("image");

                Products product = new Products(productID, productName, productImg, price);
                Cart cart = new Cart(cartID, userId, quantity, addedAt, product);

                return cart;
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy giỏ hàng: " + e.getMessage());
        }

        return null;
    }

    public void addToCart(int userID, int productID, int quantity) {
        String sql = "INSERT INTO tblCart (userID, productID, quantity, addedAt) VALUES (?, ?, ?, GETDATE())";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setInt(2, productID);
            ps.setInt(3, quantity);
            ps.executeUpdate();
            System.out.println("✅ Sản phẩm đã được thêm vào giỏ hàng!");
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi thêm vào giỏ hàng: " + e.getMessage());
        }
    }

    public void deleteProductFromCart(int cartID) {
        String sql = "DELETE FROM tblCart WHERE cartID = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, cartID);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Sản phẩm đã được xóa khỏi giỏ hàng!");
            } else {
                System.out.println("❌ Không tìm thấy sản phẩm trong giỏ hàng.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi xóa sản phẩm khỏi giỏ hàng: " + e.getMessage());
        }
    }

    public boolean clearCartByUserId(int id) {
        String sql = "DELETE FROM tblCart WHERE userID = ?";

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi xóa sản phẩm khỏi giỏ hàng: " + e.getMessage());
        }
        return false;
    }

    public void updateCart(int cartID, int quantity) {
        String sql = "UPDATE tblCart SET quantity = ? WHERE cartID = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, cartID);
            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Giỏ hàng đã được cập nhật!");
            } else {
                System.out.println("❌ Không tìm thấy sản phẩm trong giỏ hàng.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi cập nhật giỏ hàng: " + e.getMessage());
        }
    }

    public List<Cart> getCartByUserId(int userID) {
        List<Cart> cartList = new ArrayList<>();
        String sql = "SELECT * FROM tblCart c\n"
                + "join tblProduct p\n"
                + "on c.productID = p.productID\n"
                + "WHERE c.userID = ?\n"
                + "\n"
                + "";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();
            while (rs.next()) {
                int cartID = rs.getInt("cartID");
                int productID = rs.getInt("productID");
                int quantity = rs.getInt("quantity");
                Timestamp addedAt = rs.getTimestamp("addedAt");

                String productName = rs.getString("productName");
                double price = rs.getDouble("price");
                String productImg = rs.getString("image");

                Products product = new Products(productID, productName, productImg, price);
                Cart cart = new Cart(cartID, userID, quantity, addedAt, product);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            System.out.println("❌ Lỗi khi lấy giỏ hàng: " + e.getMessage());
        }
        return cartList;
    }
}
