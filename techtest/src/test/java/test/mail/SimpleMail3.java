package test.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SimpleMail3 {

    private static final String SMTP_HOST_NAME = "mail.ivision-china.cn";
    private static final String SMTP_AUTH_USER = "shi-lian.wang@ivision-china.cn";
    private static final String SMTP_AUTH_PWD = "P@$$w0rd";

    // Only for Test, please delete for product
    public static void main(String[] args) throws Exception {
        new SimpleMail3().test();
    }
//
    public void test() throws Exception {
        String from = "shi-lian.wang@ivision-china.cn";
        ArrayList<String> aTo = new ArrayList<String>();
        aTo.add("wangshilian@gmail.com");
        aTo.add("shi-lian.wang@ivision-china.cn");
        String contents = "This is a test for javamail";        
        
        SimpleMail3.send(from,aTo,contents);
        
    }
    // 

    public static void send(String from, List<String> tos, String contents) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.auth", "true");

            Authenticator auth = new SMTPAuthenticator();
            Session mailSession = Session.getDefaultInstance(props, auth);
            // uncomment for debugging infos to stdout
            // mailSession.setDebug(true);
            Transport transport = mailSession.getTransport();

            MimeMessage message = new MimeMessage(mailSession);
//            message.setContentLanguage(new String[]{"gb2312"});
            message.setContent("This is a test for 大家好", "text/plain;charset=gb2312");
            message.setFrom(new InternetAddress(from));
            for(String to : tos){
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));    
            }
            transport.connect();
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }
}