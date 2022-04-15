package ru.TagAll.tenderHackBack.application.auth.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.auth.model.AuthDto;
import ru.TagAll.tenderHackBack.application.auth.model.AccessDto;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.auth.model.TokenDto;
import ru.TagAll.tenderHackBack.application.auth.service.AuthService;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;

/**
 * Контроллер сервиса авторизации.
 *
 * @author Iurii Babalin.
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер для входа в систему")
public class AuthController {

    /**
     * {@link AuthService}.
     */
    private final AuthService authService;

    /**
     * Регистрация в приложении.
     *
     * @param registrationDto модель паредачи данных пользователя для регистрации.
     * @return 2 токена доступа.
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Auth.REGISTRATION)
    public TokenDto registration(@RequestBody RegistrationDto registrationDto) {
        return authService.registration(registrationDto);
    }

    /**
     * Вход пользователя в приложение.
     *
     * @param authDto модель для передачи данных пользователя.
     * @return 2 токена доступа.
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Auth.AUTH)
    public TokenDto authInApp(@RequestBody AuthDto authDto) {
        return authService.auth(authDto);
    }

    /**
     * выход из приложения.
     *
     * @param accessToken токен доступа.
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Auth.LOGOUT)
    public void logout(@RequestBody AccessDto accessToken) {
        authService.logout(accessToken);
    }

    /**
     * Обновленеи пароля.
     *
     * @param email почта.
     * @return ссылка на страницу востановления пароля.
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Auth.UPDATE_PASSWORD)
    public String updatePassword(@RequestBody String email) {
        return authService.updatePassword(email);
    }

    @PostMapping(Endpoints.Auth.UPDATE_TOKEN)
    public TokenDto updateAuthToken(@RequestBody AccessDto accessToken){
        return authService.updateAuthToken(accessToken);
    }

}
