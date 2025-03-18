/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.mail.MessagingException;
import strategy.EmailSender;

/**
 *
 * @author TNO
 */
public class EmailService {
    private EmailSender emailSender;


    public EmailService(EmailSender emailSender) {
        this.emailSender = emailSender;
    }
    
    public void sendEmail(String to, String subject, String messageText) throws MessagingException {
        emailSender.sendEmail(to, subject, messageText);
    }
    
    
}
