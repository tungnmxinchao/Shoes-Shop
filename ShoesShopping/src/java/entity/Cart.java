/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

public class Cart {

    private int cartID;
    private int userID;
    private int quantity;
    private Timestamp addedAt;

    private Products product;

    public Cart() {
    }

    public Cart(int cartID, int userID, int quantity, Timestamp addedAt, Products product) {
        this.cartID = cartID;
        this.userID = userID;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.product = product;
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Timestamp addedAt) {
        this.addedAt = addedAt;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Cart{" + "cartID=" + cartID + ", userID=" + userID + ", quantity=" + quantity + ", addedAt=" + addedAt + ", product=" + product + '}';
    }

}
