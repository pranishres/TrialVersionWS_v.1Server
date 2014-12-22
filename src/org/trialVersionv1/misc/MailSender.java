package org.trialVersionv1.misc;


//http://stackoverflow.com/questions/3649014/send-email-using-java

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailSender{

/*	public MailSender(){

	}
*/
	public boolean sendMail(String receiver, String subject, String body){

		final String username = "laddaSingh921@gmail.com";
        final String password = "HelloLadda";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("laddaSingh921@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiver));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
           
           return true;

        } catch (MessagingException e) {
        	System.out.println("Error while sending message : " +e.getMessage());
            return false;
        }

	}
	public static void main(String[] args) {
		MailSender ms = new MailSender();
		Boolean b = ms.sendMail("pranish.stha@gmail.com", "Registration Key", "This is your registration key. Thank you for being our member");
		if(b){
			System.out.println("Sent");
		}else {System.out.println("Unsent");}
	}


}