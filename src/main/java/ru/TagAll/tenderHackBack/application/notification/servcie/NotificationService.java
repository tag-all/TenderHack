package ru.TagAll.tenderHackBack.application.notification.servcie;

import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;

import java.util.List;

/**
 * @author Semyon Shibaev.
 */

public interface NotificationService {

    /**
     *
     * @param noticeType тип уведомления
     * @return лист уведомлений по типу
     */

    List<NotificationDto> getNoticeByType(String noticeType);

    /**
     * @param noticeType тип уведомления
     * @return количество уведомлений по типу
     */

    int getCountTypedNotice(String noticeType);



}
