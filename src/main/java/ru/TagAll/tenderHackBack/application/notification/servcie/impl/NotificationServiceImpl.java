package ru.TagAll.tenderHackBack.application.notification.servcie.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
    public List<NotificationDto> getNoticeByCustomerIdAndType(Long customerId, String noticeType) {
        List<Notification> notifications = notificationRepository.findAllByCustomer_IdAndMessage_Type(customerId, NotificationType.valueOf(noticeType));
        return convertNoticeToNoticeDto(notifications);
    }

    @Override
    public int getCountTypedNotice(Long customerId, String noticeType) {
        return notificationRepository.countNotificationByCustomer_IdAAndMessage_Type(customerId, NotificationType.valueOf(noticeType));
    }
}
