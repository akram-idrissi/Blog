
package ma.util;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;


public class MailUtilGmail {
    
    public static void sendMail(String to, String subject,
            String body, boolean isBodyHtml) 
             {

        final String password = "hahahihi";
        final String email = "dodo97931@gmail.com"; 
        
        // making a mail session 
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                        @Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});
        
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(email));
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            message.setRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            message.setSubject(subject);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (isBodyHtml)
            try {
                message.setContent(body, "text/html");
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        else
            try {
                message.setText(body);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            Transport.send(message);
        } catch (MessagingException ex) {
            Logger.getLogger(MailUtilGmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
