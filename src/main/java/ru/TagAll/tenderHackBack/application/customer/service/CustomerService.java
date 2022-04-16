package ru.TagAll.tenderHackBack.application.customer.service;

import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;

/**
 * Сеовис работы с пльзователем.
 *
 * @author Iurii Babalin.
 */
public interface CustomerService {
    /**
     * Получение данных опользователе.
     *
     * @return {@link CustomerDto}.
     */
    CustomerDto getCustomer();
}
