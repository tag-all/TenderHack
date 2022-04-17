package ru.TagAll.tenderHackBack.application.mailSender.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.mailSender.service.MailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Semyon Shibaev.
 */
@Service
public class MailSenderImpl implements MailSender {

    /**
     * Реализация почтового клиента
     *
     * @param mail - на какую почту отправляем
     * @param messageText - текст сообщения
     */

    private static String mesSubject = "Tag All - уведомление о котировочной сессии";

    private static String userName = "shibaev.job@gmail.com";

    private static String password = "nbabcegenjeynizx";


    @Override
    public void sendMail(String mail, String messageText) throws MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "587");

        Session session = Session.getInstance(properties,
                //Аутентификатор - объект, который передает логин и пароль
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });

        //Создаем новое почтовое сообщение
        Message message = new MimeMessage(session);
        //От кого
        message.setFrom(new InternetAddress(userName));
        //Кому
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        //Тема письма
        message.setSubject(mesSubject);
        //Текст письма
        message.setText(messageText);
        //Поехали!!!
        Transport.send(message);
    }
}






