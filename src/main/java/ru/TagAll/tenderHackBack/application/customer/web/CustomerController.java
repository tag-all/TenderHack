package ru.TagAll.tenderHackBack.application.customer.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.application.customer.model.SessionsAuto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;

/**
 * Контроллер работы с пользователем.
 *
 * @author Semyon Shibaev.
 * @author Iurii Babalin.
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "Работа с пользователем")
public class CustomerController {

    /**
     * {@link CustomerService}.
     */
    private final CustomerService customerService;

    /**
     * Получение активных сессий
     */
    @BadRequestSystemError
    @GetMapping(value = Endpoints.Customer.ACTIVE_SESSIONS)
    public SessionsDto getActiveSessions() {
        return customerService.getAllActiveSessions();
    }

    /**
     * Обновление профиля пользователя.
     */
    @BadRequestSystemError
    @PostMapping(Endpoints.Customer.PROFILE_UPDATE)
    public void updateProfile(@RequestBody CustomerDto customerDto){
        customerService.updateProfile(customerDto);
    }

    /**
     * Получение сессий в ручном режиме
     */
    @BadRequestSystemError
    @GetMapping(value = Endpoints.Customer.MANUAL_SESSIONS)
    public SessionsDto getManualSessions() {
        return customerService.getAllManualSessions();
    }


    /**
     * Получение сессий в автоматическом режиме
     */
    @BadRequestSystemError
    @GetMapping(value = Endpoints.Customer.AUTO_SESSIONS)
    public SessionsAuto getAutoSessions() {
        return customerService.getAllAutoSessions();
    }

    /**
     * Получение информации о сессии
     */
    @BadRequestSystemError
    @GetMapping(value = Endpoints.Customer.SESSION_INFO)
    public SessionDto getSessionInfo(@PathVariable Long sessionId) {
        return customerService.getSessionInfo(sessionId);
    }

    /**
     * Поставить ставку
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Customer.PLACE_BET)
    public void placeBet(@PathVariable Long sessionId) {
        customerService.placeManualBet(sessionId);
    }

    /**
     * Получение данных о пользователе.
     */
    @BadRequestSystemError
    @GetMapping(Endpoints.Customer.GET_CUSTOMER_PROFILE)
    public CustomerDto getProfile(){
        return customerService.getCustomer();
    }
}
