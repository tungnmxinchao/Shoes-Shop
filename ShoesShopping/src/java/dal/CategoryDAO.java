/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Categories;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author TNO
 */
public class CategoryDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;

    private Vector<Categories> categories;

    public CategoryDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
        categories = new Vector<>();
    }

    public Vector<Categories> findAll() {
        try {
            String query = "SELECT categoryID, categoryName FROM tblCategory";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Categories category = new Categories(rs.getInt("categoryID"), rs.getString("categoryName"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
