package ru.TagAll.tenderHackBack.application.customer.model;

import lombok.Data;

/**
 * Модель данных пользователя.
 */
@Data
public class CustomerDto {
    private String email;
    private String companyName;
    private String notificationDelay;
}
