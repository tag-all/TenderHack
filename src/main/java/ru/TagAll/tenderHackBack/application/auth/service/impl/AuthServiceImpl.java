package ru.TagAll.tenderHackBack.application.auth.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.errors.ErrorDescription;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;
import ru.TagAll.tenderHackBack.utils.JwtUtils;
import ru.TagAll.tenderHackBack.application.auth.domain.Token;
import ru.TagAll.tenderHackBack.application.auth.domain.TokenRepository;
import ru.TagAll.tenderHackBack.application.auth.model.AuthDto;
import ru.TagAll.tenderHackBack.application.auth.model.AccessDto;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.auth.model.TokenDto;
import ru.TagAll.tenderHackBack.application.auth.service.AuthService;

/**
 * Логика сервисе авторизации.
 *
 * @author Iurii Babalin.
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    /**
     * {@link CustomerRepository}.
     */
    private final CustomerRepository customerRepository;

    /**
     * {@link TokenRepository}.
     */
    private final TokenRepository tokenRepository;

    /**
     * {@link JwtUtils}.
     */
    private final JwtUtils jwtUtils;

    /**
     * Вход пользователя в приложение.
     *
     * @param authDto модель для передачи данных пользователя.
     * @return 2 токена доступа.
     */
    @Override
    public TokenDto auth(AuthDto authDto) {
        log.info("auth init({})", authDto);
        Customer customer = customerRepository.getCustomerByEmail(authDto.getLogin());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ErrorDescription.CUSTOMER_NOT_FOUND.throwIfTrue(ObjectUtils.isEmpty(customer));
        ErrorDescription.CUSTOMER_PASSWORD_ERROR.throwIfFalse(passwordEncoder.matches(authDto.getPassword(),
                customer.getPassword()));
        Token token = new Token();
        token.setCustomer(customer);
        token.setToken(jwtUtils.generateToken(customer.getEmail().concat(customer.getPassword()
                .concat(customer.getId().toString()))));
        tokenRepository.save(token);
        log.info("auth completed");
        return TokenDto.of(jwtUtils.generateToken(customer.getEmail()), token.getToken());
    }

    /**
     * Регистрация в приложении.
     *
     * @param registrationDto модель паредачи данных пользователя для регистрации.
     * @return 2 токена доступа.
     */
    @Override
    public TokenDto registration(RegistrationDto registrationDto) {
        log.info("registration init({})", registrationDto);
        ErrorDescription.CUSTOMER_FOUND.throwIfFalse(ObjectUtils.isEmpty(customerRepository
                .getCustomerByEmail(registrationDto.getLogin())));
        Customer customer = customerRepository.save(ConvertorUtils.convertRegistrationDtoToCustomer(registrationDto));
        Token token = new Token();
        token.setCustomer(customer);
        token.setToken(jwtUtils.generateToken(customer.getEmail().concat(customer.getPassword()
                .concat(customer.getId().toString()))));
        tokenRepository.save(token);
        log.info("registration completed");
        return TokenDto.of(jwtUtils.generateToken(customer.getEmail()), token.getToken());
    }

    /**
     * выход из приложения.
     *
     * @param accessToken токен доступа.
     */
    @Override
    public void logout(AccessDto accessToken) {
        log.info("logout init({})", accessToken);
        ErrorDescription.CUSTOMER_LOGOUT_ERROR.throwIfTrue(ObjectUtils.isEmpty(tokenRepository
                .getTokenByToken(accessToken.getAccessToken())));
        tokenRepository.delete(tokenRepository.getTokenByToken(accessToken.getAccessToken()));
        log.info("logout completed");
    }

    /**
     * Обновленеи пароля.
     *
     * @param email почта.
     * @return ссылка на страницу востановления пароля.
     */
    @Override
    public String updatePassword(String email) {
        return null;
    }

}
