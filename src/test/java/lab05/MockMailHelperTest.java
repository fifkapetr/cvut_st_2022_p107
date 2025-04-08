package lab05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import static org.mockito.Mockito.mockConstruction;

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

    /*
    🔧 Stubování (nastavení návratové hodnoty): Mock findMail() vrací připravený Mail.
    📌 Cíl: Ověřit, že sendMail pošle správný mail (zavolá saveMail se správným objektem).
    💡 Tip: Může být fajn vysvětlit rozdíl mezi “mock” (ověřování volání) a “stub” (nastavení návratové hodnoty).
    */
    @Test
    public void testStubedMail() {
        int mailId = Mockito.anyInt();
        Mockito.when(mockDBManager.findMail(mailId)).thenReturn(getMail());
        mailHelper.sendMail(mailId);
        Mockito.verify(mockDBManager).saveMail(getMail());
    }

    /*
    📨 Kontrola konkrétní hodnoty: Ověřujeme, že objekt, který MailHelper používá, má správné údaje.
    ✅ Už testujeme logiku: Jestli se správně propíše mail do interního stavu MailHelper.
    */
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

/*
    💡 Shrnutí pro studenty:
        •	Mockito.spy() vytváří částečný mock – tedy obalí reálný objekt, ale dovolí sledovat jeho chování nebo některé metody přepsat (stubnout).
        •	Výhodné, pokud chceme testovat reálnou logiku, ale zároveň chceme mít nad něčím kontrolu.
    🔧 Praktický příklad – Spy nad MailHelper
    Cílem je:
        •	vytvořit Spy objekt nad reálnou instancí MailHelper,
        •	sledovat volání metod,
        •	případně nějakou metodu „vypnout“ (např. sendMail()), aby se e-mail neposílal.
    Krok
    spy(realObj) - Vytváří Spy - volání reálných metod
    doNothing().when(...).sendMail(...) - Zabraňuje skutečnému odesílání mailu
    verify(...) -  ověřuje, že se metoda zavolala

 */
    @Test
    public void spyTest() {
        // Vytvoříme reálný objekt
        MailHelper realMailHelper = new MailHelper(new DBManager());

        // Vytvoříme Spy
        MailHelper spyMailHelper = Mockito.spy(realMailHelper);

        // Stubujeme metodu sendMail, aby se nespouštěla skutečná logika
        Mockito.doNothing().when(spyMailHelper).sendMail(Mockito.anyInt());

        // Vypneme debug mód, aby se zavolala sendMail
        Configuration.isDebug = false;

        // Voláme naši testovanou metodu
        spyMailHelper.createAndSendMail("test@cvut.cz", "Hello", "Body");

        // Ověřujeme, že se zavolala metoda sendMail
        Mockito.verify(spyMailHelper).sendMail(Mockito.anyInt());
    }

}
