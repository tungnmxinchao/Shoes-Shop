/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author TNO
 */
public class OrderDetails {
    private int detailID;
    private double price;
    private int quantity;
    
    private Order order;
    
    private Products product;

    public OrderDetails() {
    }

    public OrderDetails(int detailID, double price, int quantity, Order order, Products product) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "detailID=" + detailID + ", price=" + price + ", quantity=" + quantity + ", order=" + order + ", product=" + product + '}';
    }
    
    
    
    
}
