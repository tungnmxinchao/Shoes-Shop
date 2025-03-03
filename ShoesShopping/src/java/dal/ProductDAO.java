/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import static constant.Constant.RECORD_PER_PAGE;
import entity.Categories;
import entity.Products;
import java.sql.*;
import java.util.Vector;

public class ProductDAO extends DBContext {
    
    private PreparedStatement ps;
    private ResultSet rs;
    
    private Vector<Products> vectorProduct;
    
    public ProductDAO() {
        vectorProduct = new Vector<>();
    }
    
    public static void main(String[] args) {
        
    }
    
    public int findTotalRecord() {
        String sql = "select count(p.productID) from tblProduct p\n"
                + "where p.[status] = 1 ";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    public int findTotalBySearch(String name) {
        String sql = "select count(p.productID) from tblProduct p\n"
                + "join tblCategory c\n"
                + "on p.categoryID = c.categoryID\n"
                + "where p.[status] = 1 and p.productName like ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, "%" + name + "%");
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    
    public Vector<Products> findByPage(int page) {
        String sql = "select * from tblProduct p\n"
                + "join tblCategory c\n"
                + "on p.categoryID = c.categoryID\n"
                + "where p.[status] = 1 \n"
                + "ORDER BY p.ProductID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            ps.setInt(1, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(2, RECORD_PER_PAGE);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Products product = getProductFromResultSet(rs);
                vectorProduct.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return vectorProduct;
        
    }
    
    public Vector<Products> searchByName(String name, int page) {
        String sql = "select * from tblProduct p\n"
                + "join tblCategory c\n"
                + "on p.categoryID = c.categoryID\n"
                + "where p.[status] = 1 and p.productName like ?\n"
                + "ORDER BY p.ProductID\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, (page - 1) * RECORD_PER_PAGE);
            ps.setInt(3, RECORD_PER_PAGE);
            
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Products product = getProductFromResultSet(rs);
                vectorProduct.add(product);
            }
            
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        return vectorProduct;
    }
    
    private Products getProductFromResultSet(ResultSet rs) throws SQLException {
        int productID = rs.getInt("productID");
        String productName = rs.getString("productName");
        String image = rs.getString("image");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity");
        String categoryID = rs.getString("categoryID");
        Date importDate = rs.getDate("importDate");
        Date usingDate = rs.getDate("usingDate");
        int status = rs.getInt("status");
        
        String categoryName = rs.getString("categoryName");
        
        Categories category = new Categories(categoryID, categoryName, "");
        
        return new Products(productID, productName, image, price, quantity, category, importDate, usingDate, status);
    }
    
    public void insertProduct(Products products) {
        String sql = "INSERT INTO [dbo].[tblProducts]\n"
                + "([productName], [image], [price], [quantity], [categoryID], [importDate], [usingDate], [status])\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Make sure all columns are in the VALUES clause

        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);

            // Set the parameters in the correct order
            ps.setString(1, products.getProductName());
            ps.setString(2, products.getImage());
            ps.setDouble(3, products.getPrice());
            ps.setInt(4, products.getQuantity());
            ps.setString(5, products.getCategory().getCategoryId());
            ps.setDate(6, products.getImportDate());
            ps.setDate(7, products.getUsingDate());
            ps.setInt(8, products.getStatus()); // This is the 8th parameter

            ps.executeUpdate();
            System.out.println("✅ Insert thành công!"); // Confirm success

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(); // To get more detailed error info
        }
    }
    
    public Products searchProductById(int productID) {
        String sql = "select * from tblProduct where productID = ?";
        Products pro = null;
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                pro = new Products();
                pro.setProductID(rs.getInt(1));
                pro.setProductName(rs.getString(2));
                pro.setImage(rs.getString(3));
                pro.setPrice(rs.getDouble(4));
                pro.setQuantity(rs.getInt(5));
                pro.setCategory(new Categories(rs.getString(6), "", ""));
                pro.setImportDate(rs.getDate(7));
                pro.setUsingDate(rs.getDate(8));
                pro.setStatus(rs.getInt("status"));
                
            } else {
                System.out.println("Not Found " + productID);
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pro;
    }
    
    public void updateProductByID(Products p) {
        String sql = "UPDATE [dbo].[tblProducts]\n"
                + "   SET [productName] = ?\n"
                + "      ,[image] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[categoryID] = ?\n"
                + "      ,[importDate] = ?\n"
                + "      ,[usingDate] = ?\n"
                + "      ,[status] = ?\n"
                + " WHERE productID = ?";
        
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getImage());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setString(5, p.getCategory().getCategoryId());
            ps.setDate(6, p.getImportDate());
            ps.setDate(7, p.getUsingDate());
            ps.setInt(8, p.getStatus());
            ps.setInt(9, p.getProductID());
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int deleteProductById(int productsID) {
        String sql = "DELETE FROM [dbo].[tblProducts]\n"
                + "      WHERE productID = ?";
        int n = 0;
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, productsID);
            rs = getData("SELECT *\n"
                    + "  FROM [dbo].[tblOrderDetails]\n"
                    + "  where productID = " + productsID);
            
            if (rs.next() && rs != null) {
                changeStatus(productsID, 0);
                return n;
            }
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return n;
    }
    
    public void changeStatus(int productsID, int status) {
        String sql = "UPDATE [dbo].[tblProducts]\n"
                + "   SET [status] = ?\n"
                + " WHERE productID = ?";
        try (Connection connection = new DBContext().connection) {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, status);
            ps.setInt(2, productsID);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
