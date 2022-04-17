package ru.TagAll.tenderHackBack.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель для передачи данных пользователя на фвторизацию.
 *
 * @author Iurii Babalin.
 */
@Data
public class AuthDto {
    @Schema(name = "Логин пользователя (email)")
    private String login;
    @Schema(name = "Пароль пользователя")
    private String password;
}
