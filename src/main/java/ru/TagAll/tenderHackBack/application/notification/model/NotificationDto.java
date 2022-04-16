package ru.TagAll.tenderHackBack.application.notification.model;


import lombok.Data;

/**
 * Модель сообщения для пользователя.
 */

@Data
public class NotificationDto {
        private String id;
        private String message;
        private String type;
}
