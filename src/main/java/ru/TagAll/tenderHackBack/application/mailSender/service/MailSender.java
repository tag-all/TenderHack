package ru.TagAll.tenderHackBack.application.mailSender.service;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author Semyon Shibaev.
 */

public interface MailSender {

    /**
     * Отправка сообщений на почту
     * @param mail - почта получателя
     * @param messageText - тело письма
     * @throws MessagingException
     */

    void sendMail(String mail, String messageText) throws MessagingException;

}
