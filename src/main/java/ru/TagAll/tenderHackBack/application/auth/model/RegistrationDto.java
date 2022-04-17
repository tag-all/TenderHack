package ru.TagAll.tenderHackBack.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель для передачи данных пользователя на регистрацию.
 *
 * @author Iurii Babalin.
 */
@Data
public class RegistrationDto extends AuthDto {
    @Schema(name = "Имя пользователя")
    private String companyName;
}
