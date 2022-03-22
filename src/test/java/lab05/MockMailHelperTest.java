package lab05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockMailHelperTest {

    DBManager mockDBManager = Mockito.mock(DBManager.class);
    MailHelper mailHelper = new MailHelper(mockDBManager);
    Mail mail = new Mail();

    @Test
    public void mockTest() {
        mailHelper.sendMail(1);
        Mockito.verify(mockDBManager).findMail(1);
    }

    @Test
    public void negativeMockTest() {
        mailHelper.sendMail(1);
        Mockito.verify(mockDBManager, Mockito.times(2)).findMail(1);
    }

    @Test
    public void testStubedMail() {
        int mailId = Mockito.anyInt();
        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(getMail());
        mailHelper.sendMail(mailId);
        Mockito.verify(mockDBManager).saveMail(getMail());
    }

    @Test
    public void testStubMailTo() {
        int mailId = Mockito.anyInt();
        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(getMail());
        mailHelper.sendMail(mailId);
        Assertions.assertEquals("ABCD", mailHelper.getMail().getTo());
    }

    private Mail getMail() {
        mail.setTo("ABCD");
        mail.setBody("BODY");
        mail.setSubject("SUBJECT");
        return mail;
    }
}
