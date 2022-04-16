package ru.TagAll.tenderHackBack.application.customer.model;

import lombok.Data;

import java.sql.Time;

/**
 * Модель данных пользователя.
 */
@Data
public class CustomerDto {
    private String email;
    private String companyName;
    private Time notificationDelay;
}
