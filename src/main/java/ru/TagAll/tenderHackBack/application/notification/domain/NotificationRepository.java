package ru.TagAll.tenderHackBack.application.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationType;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findAllByCustomer_IdAndMessage_Type(Long customerId, NotificationType type);

    int countNotificationByCustomer_IdAAndMessage_Type(Long customerId, NotificationType type);

}
