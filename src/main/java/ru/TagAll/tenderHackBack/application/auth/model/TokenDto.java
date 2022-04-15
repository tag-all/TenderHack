package ru.TagAll.tenderHackBack.application.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Модель для передачи токена пользователю.
 *
 * @author Iurii Babalin.
 */
@Data
@AllArgsConstructor(staticName = "of")
public class TokenDto {
    @Schema(name = "Токен доступа к приожению")
    private String authToken;
    @Schema(name = "Токен для получения authToken и выхода из приложения")
    private String accessToken;
}