package ru.TagAll.tenderHackBack.application.notification.servcie.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.application.notification.domain.MessageRepository;
import ru.TagAll.tenderHackBack.application.notification.domain.Notification;
import ru.TagAll.tenderHackBack.application.notification.domain.NotificationRepository;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationType;
import ru.TagAll.tenderHackBack.application.notification.servcie.NotificationService;
import java.util.List;

import static ru.TagAll.tenderHackBack.utils.ConvertorUtils.*;

/**
 * Сервис уведомлений.
 *
 * @author Iurii Babalin
 * @author Semyon Shibaev
 */
@Service
@AllArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public List<NotificationDto> getNoticeByType(String noticeType) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Notification> notifications = notificationRepository.findAllByCustomerAndMessage_Type(customer, NotificationType.valueOf(noticeType));
        return convertNoticeToNoticeDto(notifications);
    }


    @Override
    public int getCountTypedNotice(String noticeType) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return notificationRepository.countNotificationByCustomerAndMessage_Type(customer, NotificationType.valueOf(noticeType));
    }
}
