package check.speed;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSent {

	public static Transport transport;

	public void sendingNewEmail() {
		
		 // Recipient's email ID needs to be mentioned.
	      String to = "ramesh.saini@ubuy.com";

	      // Sender's email ID needs to be mentioned
	      String from = "ubuyramesh@gmail.com";

	      final String username = "ubuyramesh@gmail.com";//change accordingly
	      final String password = "ubuy@123";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.transport.protocol", "smtp");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "465");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.ssl.enable", "true");
	      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");	
	      
	      

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject("Speed check Data");

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText("Greetings of the day!");
	        messageBodyPart.setText("page load time for search and detail page");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	        // String filename = TakeSS.errorscreentshots;
	         String fileName=(System.getProperty("user.dir")+"/searchSpeed.txt");
	         System.out.println("here");	         
	         DataSource source = new FileDataSource(fileName);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         messageBodyPart.setFileName(fileName);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         
	         // Send message
	         try {
	         //transport.connect(host, username, password);
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	        // throw new RuntimeException(e);
	    	  e.printStackTrace();
	      }
		
	}catch (MessagingException e) {
       // throw new RuntimeException(e);
		e.printStackTrace();
     }
}
	
}
