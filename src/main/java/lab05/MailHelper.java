package lab05;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 *
 * @author balikm1
 */
public class MailHelper {

    private Mail mail;
    private final DBManager dbManager;
    protected Thread sendingThread; // keep the thread reference for synchronization in unit tests

    public Mail getMail() {
        return mail;
    }

    public MailHelper(DBManager dbManager)
    {
        this.dbManager = dbManager;
    }
    
    public void createAndSendMail(String to, String subject, String body)
    {
        Mail mail = new Mail();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setBody(body);
        mail.setIsSent(false);
        insertMailToDb(mail);

        sendMailAsync(mail.getMailId());
    }
    
    public void sendMail(int mailId)
    {
        try
        {
            // get entity
            mail = loadMail(mailId);
            if (mail == null) {
                return;
            }

            if (mail.isSent()) {
                return;
            }

            String from = "user@fel.cvut.cz";
            String smtpHostServer = "smtp.cvut.cz";
            Properties props = System.getProperties();
            props.put("mail.smtp.host", smtpHostServer);
            Session session = Session.getInstance(props, null);
            MimeMessage message = new MimeMessage(session);

            message.setFrom(from);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail.getTo(), false));
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody(), "UTF-8");

            // send
            sendMail(message);
            mail.setIsSent(true);
            saveMailChanges(mail);
        }
        catch (Exception e) {
          e.printStackTrace();
        }
    }
    
    protected void insertMailToDb(Mail mail) {
        //DBManager dbManager = new DBManager();
        dbManager.saveMail(mail);
    }
    
    protected void sendMailAsync(int mailId)
    {
        if (!Configuration.isDebug) {
            (sendingThread = new Thread(() -> {
                sendMail(mailId);
            })).start();
        }
    }
    
    protected Mail loadMail(int mailID)
    {
        //DBManager dbManager = new DBManager();
        return dbManager.findMail(mailID);
    }
    
    protected void saveMailChanges(Mail mail)
    {
        dbManager.saveMail(mail);
    }

    protected void sendMail(MimeMessage message) throws MessagingException
    {
        System.out.println("Mail is sent :)");
        //TODO Implement Transport.send(message)
        //Transport.send(message);
    }   
}