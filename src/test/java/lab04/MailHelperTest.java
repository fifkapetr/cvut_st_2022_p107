package lab04;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MailHelperTest {

    String to = "Receiver";
    String subject = "Subject of mail";
    String body = "Mail body";
    Mail mail;

    @BeforeEach
    public void setMail(){
        MailHelper mailHelper = new MailHelper();
        mailHelper.createAndSendMail(to, subject, body);
        mail = mailHelper.getMail();
    }

    @Test
    public void setTo_toIsSetToMail_equals() {
        Assertions.assertEquals(to, mail.getTo());
    }

    @Test
    public void setSubject_subjectIsSetToMail_equals() {
        Assertions.assertEquals(subject, mail.getSubject());
    }

    @Test
    public void setBody_bodyIsSetToMail_equals() {
        Assertions.assertEquals(body, mail.getBody());
    }

    @Test
    public void sentMail_mailIsSent_true() {
       Assertions.assertTrue(mail.isSent());
    }
}
