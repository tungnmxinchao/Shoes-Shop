/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import entity.Role;
import entity.Users;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author TNO
 */
public class UserDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection conn;
    private Vector<Users> users;

    public UserDAO() {
        if (conn == null) {
            conn = new DBContext().connection;
        }
        users = new Vector<>();
    }

    public Users findUserByEmailAndPass(String email, String pass) {
        Users user = null;
        try {
            String query = "SELECT * FROM tblUsers WHERE email = ? AND password = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users(rs.getInt("userID"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"),
                        rs.getString("email"), new Role(rs.getInt("roleID"), ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Users findUserById(int id) {
        Users user = null;
        try {
            String query = "SELECT * FROM tblUsers WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new Users(rs.getInt("userID"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"),
                        rs.getString("email"), new Role(rs.getInt("roleID"), ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public boolean addUser(Users user) {
        try {
            String query = "INSERT INTO tblUsers (fullName, password, roleID, address, birthday, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPass());
            ps.setInt(3, user.getRole().getRoleId());
            ps.setString(4, user.getAddress());
            ps.setDate(5, user.getBirthday());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(Users user) {
        try {
            String query = "UPDATE tblUsers SET fullName = ?, password = ?, roleID = ?, address = ?, birthday = ?, phone = ?, email = ? WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getPass());
            ps.setInt(3, user.getRole().getRoleId());
            ps.setString(4, user.getAddress());
            ps.setDate(5, user.getBirthday());
            ps.setString(6, user.getPhone());
            ps.setString(7, user.getEmail());
            ps.setInt(8, user.getUserId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(int id) {
        try {
            String query = "DELETE FROM tblUsers WHERE userID = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vector<Users> findAll() {
        try {
            String query = "SELECT * FROM tblUsers";
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users(rs.getInt("userID"), rs.getString("fullName"),
                        rs.getString("password"), rs.getString("address"),
                        rs.getDate("birthday"), rs.getString("phone"),
                        rs.getString("email"), new Role(rs.getInt("roleID"), ""));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
