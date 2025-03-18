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
public class Payment {
    private int paymentId;
    private int orderId;
    private Timestamp paymentDate;
    private String paymentStatus;
    private String transactionId;
    private double totalAmount;

    public Payment() {
    }

    public Payment(int paymentId, int orderId, Timestamp paymentDate, String paymentStatus, String transactionId, double totalAmount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.transactionId = transactionId;
        this.totalAmount = totalAmount;
    }

    

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentId=" + paymentId + ", orderId=" + orderId + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + ", transactionId=" + transactionId + ", totalAmount=" + totalAmount + '}';
    }

    
    
     
}
