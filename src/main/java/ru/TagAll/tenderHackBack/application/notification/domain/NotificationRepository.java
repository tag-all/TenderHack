package ru.TagAll.tenderHackBack.application.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationType;

import java.util.List;


/**
 * Работа с тоблицей уведомлений
 *
 * @author Semyon Shibaev
 */
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findAllByCustomerAndMessage_Type(Customer customer, NotificationType type);

    int countNotificationByCustomerAndMessage_Type(Customer customer, NotificationType type);

}
