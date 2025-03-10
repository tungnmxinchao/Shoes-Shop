/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author TNO
 */
public class Order {

    private int orderId;
    private Timestamp orderDate;
    private double total;

    private Users user;

    public Order() {
    }

    public Order(int orderId, Timestamp orderDate, double total, Users user) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", orderDate=" + orderDate + ", total=" + total + ", user=" + user + '}';
    }

}
