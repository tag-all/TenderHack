package ru.TagAll.tenderHackBack.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Модель для передачи токена доступа
 *
 * @author Iurii Babalin.
 */
@Data
public class AccessDto {
    @Schema(name = "Токен доступа")
    private String accessToken;
}
