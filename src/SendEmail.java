
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendEmail
{
    public static String recepiantEmail;
    public static String subject;
    public static String emailBody;
    public static final String senderMail = "pasindu0996@gmail.com";
    public static final String senderPassword = "senhaDE{((^(^}";
    
    public static void SendEmail()
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.smtp.host", "smtp.gmail.com");
	prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication(senderMail, senderPassword);
            }
        });
        
        try
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderMail));
            
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepiantEmail));
            message.setSubject(subject);
            message.setText(emailBody);
            Transport.send(message);
            System.out.println("E-Mail Sent");
            
            
        }
        catch(MessagingException e)
        {
            throw new RuntimeException(e);
        }
    }

           

    
    
    
}