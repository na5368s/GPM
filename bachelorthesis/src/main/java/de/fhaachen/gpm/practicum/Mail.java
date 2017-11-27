package de.fhaachen.gpm.practicum;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mail implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        String email = (String) execution.getVariable("email");
        String subject = (String) execution.getVariableLocal( "subject");
        String mailText = (String) execution.getVariableLocal( "mailText");

        sendMail(subject, mailText, email);

    }

    public void sendMail(String subject, String mailText, String email) throws MessagingException {
        final Properties props = new Properties();

        // Zum Empfangen
        props.setProperty( "mail.pop3.host", "pop.gmail.com" );
        props.setProperty( "mail.pop3.user", "noelkingslyanupkumar@gmail.com" );
        props.setProperty( "mail.pop3.password", "6UALGTiJYX" );
        props.setProperty( "mail.pop3.port", "995" );
        props.setProperty( "mail.pop3.auth", "true" );
        props.setProperty( "mail.pop3.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );

        // Zum Senden
        props.setProperty( "mail.smtp.host", "smtp.gmail.com" );
        props.setProperty( "mail.smtp.auth", "true" );
        props.setProperty( "mail.smtp.port", "465" );
        props.setProperty( "mail.smtp.socketFactory.port", "465" );
        props.setProperty( "mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory" );
        props.setProperty( "mail.smtp.socketFactory.fallback", "false" );

        Session session = Session.getInstance( props, new javax.mail.Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.pop3.user"),
                        props.getProperty("mail.pop3.password"));
            }});

        Message msg = new MimeMessage( session );

        InternetAddress addressTo = new InternetAddress("noelkingslyanupkumar@gmail.com");
        msg.setRecipient( Message.RecipientType.TO, new InternetAddress(email) );

        msg.setSubject( subject);
        msg.setContent( mailText, "text/plain" );
        Transport.send( msg );
    }
}
