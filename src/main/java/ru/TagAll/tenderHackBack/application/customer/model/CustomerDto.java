package ru.TagAll.tenderHackBack.application.customer.model;

import lombok.Data;

/**
 * Модель данных пользователя.
 */
@Data
public class CustomerDto {
    private String email;
    private String name;
    private String lastName;
    private String patronymic;
    private String phone;
    private String emailConfirmed;
}
