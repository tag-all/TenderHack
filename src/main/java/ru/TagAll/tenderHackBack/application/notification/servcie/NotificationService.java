package ru.TagAll.tenderHackBack.application.notification.servcie;

import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;

import java.util.List;

/**
 * @author Semyon Shibaev.
 */

public interface NotificationService {

    /**
     * @param customerId идентификатор пользователя
     * @param noticeType тип уведомления
     * @return лист уведомлений по типу
     */

    List<NotificationDto> getNoticeByCustomerIdAndType(Long customerId, String noticeType);

    /**
     * @param customerId идентификатор пользователя
     * @param noticeType тип уведомления
     * @return количество уведомлений по типу
     */

    int getCountTypedNotice(Long customerId, String noticeType);



}
