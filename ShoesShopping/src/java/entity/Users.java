/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.sql.Date;
/**
 *
 * @author TNO
 */
public class Users {
    private int userId;
    private String fullName;
    private String pass;
    private String address;
    private Date birthday;
    private String phone;
    private String email;
    
    private Role role;

    public Users() {
    }

    public Users(int userId, String fullName, String pass, String address, Date birthday, String phone, String email, Role role) {
        this.userId = userId;
        this.fullName = fullName;
        this.pass = pass;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", fullName=" + fullName + ", pass=" + pass + ", address=" + address + ", birthday=" + birthday + ", phone=" + phone + ", email=" + email + ", role=" + role + '}';
    }
    
    
}
