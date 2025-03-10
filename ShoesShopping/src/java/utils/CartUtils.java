/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.Cart;
import java.util.List;


/**
 *
 * @author TNO
 */
public class CartUtils {
    
     public static double caculatorCart(List<Cart> cart) {
        double totalCart = 0;
        for (Cart cartItem : cart) {
            if (cartItem.getProduct() != null) {
                double price = cartItem.getProduct().getPrice();
                int quantity = cartItem.getQuantity();

                if (price >= 0 && quantity >= 0) {
                    totalCart += quantity * price;
                }
            }
        }
        return totalCart;
    }
}
