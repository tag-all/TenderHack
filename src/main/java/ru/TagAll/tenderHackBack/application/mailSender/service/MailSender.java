package ru.TagAll.tenderHackBack.application.mailSender.service;


import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 * @author Semyon Shibaev.
 */

public interface MailSender {

    void sendMail(String mail, String messageText) throws MessagingException;
}
