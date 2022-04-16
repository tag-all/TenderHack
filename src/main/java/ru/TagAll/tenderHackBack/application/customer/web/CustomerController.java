package ru.TagAll.tenderHackBack.application.customer.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;

/**
 * Контроллер работы с пользователем.
 *
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
     * Получение данных опользователе.
     *
     * @return {@link CustomerDto}.
     */
    @BadRequestSystemError
    @GetMapping(Endpoints.Customer.GET_CUSTOMER_PROFILE)
    public CustomerDto getProfile(){
        return customerService.getCustomer();
    }
}
