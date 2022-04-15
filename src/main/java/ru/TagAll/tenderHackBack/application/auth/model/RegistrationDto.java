package ru.TagAll.tenderHackBack.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель для переданы данных пользователя на регистрацию.
 *
 * @author Iurii Babalin.
 */
@Data
public class RegistrationDto extends AuthDto {
    @Schema(name = "Имя пользователя")
    private String name;
    @Schema(name = "Фамилия пользователя")
    private String lastName;
    @Schema(name = "Отчество пользователя")
    private String patronymic;
    @Schema(name = "Телефон пользователя")
    private String phone;
}
