package be.esi.g34840.carrefour.mail;

import be.esi.g34840.carrefour.exception.MailException;
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
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mailer {

    final static String username = "noreplycarrefouralg@gmail.com";
    final static String password = "a1z2e3r4!";

    public static void send(String destinataires, String sujet, String corp) throws MailException {
        send(destinataires, sujet, corp, null, false);

    }

    public static void send(String destinataires, String sujet, String corp, String filename, boolean html) throws MailException {

        if (sujet == null || corp == null || destinataires == null || sujet.equals("") || corp.equals("") || destinataires.equals("")) {
            throw new MailException("Le sujet, le corps et le destinataire ne peuvent pas etre vide ou null");
        }
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinataires));
            message.setSubject(sujet);
            if (html) {
                message.setContent(corp, "text/html");
            } else {
                message.setText(corp);
            }
            if (filename != null) {
                BodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setText(corp);
                Multipart multipart = new MimeMultipart();
                //add the message body to the mime message
                multipart.addBodyPart(messageBodyPart);
                // add any file attachments to the message
                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                //use a JAF FileDataSource as it does MIME type detection
                DataSource source = new FileDataSource(filename);
                attachmentBodyPart.setDataHandler(new DataHandler(source));
                attachmentBodyPart.setFileName(filename);
                //add the attachment
                multipart.addBodyPart(attachmentBodyPart);
                //Put all message parts in the message
                message.setContent(multipart);
            }
            Transport.send(message);
            System.out.println("sended");
        } catch (AddressException e) {
            throw new MailException("Adresse invalide\n" + e.getMessage());
        } catch (MessagingException e) {
            throw new MailException("Erreur dans le message\n" + e.getMessage());
        }
    }
}