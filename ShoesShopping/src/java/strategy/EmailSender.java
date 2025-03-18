/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import jakarta.mail.MessagingException;

/**
 *
 * @author TNO
 */
public interface EmailSender {
    void sendEmail(String to, String subject, String messageText) throws MessagingException;
}
