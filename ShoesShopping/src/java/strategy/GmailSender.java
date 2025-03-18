/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategy;

import jakarta.mail.MessagingException;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import utils.EmailConfig;

public class GmailSender implements EmailSender {

    private final String from = EmailConfig.FROM_EMAIL;
    private final String password = EmailConfig.PASSWORD;

    @Override
    public void sendEmail(String to, String subject, String messageText) throws MessagingException {
        // Set up email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", EmailConfig.SMTP_HOST);
        props.put("mail.smtp.port", EmailConfig.SMTP_PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Get the session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from, "Shoes Shop"));
        } catch (java.io.UnsupportedEncodingException e) {
            message.setFrom(new InternetAddress(from));
        }

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

        message.setSubject(subject);

        message.setText(messageText);

        message.setContent(messageText, "text/html; charset=UTF8");
        Transport.send(message);
    }

}
