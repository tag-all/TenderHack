package ru.TagAll.tenderHackBack.application.customer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

/**
 * Логика работы с пользователем.
 *
 * @author Iurii Babalin.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /**
     * Получение данных опользователе.
     *
     * @return {@link CustomerDto}.
     */
    @Override
    public CustomerDto getCustomer() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ConvertorUtils.convertCustomerToCustomerDto(customer);
    }
}
