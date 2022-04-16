package ru.TagAll.tenderHackBack.application.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Работа с тоблицей пользователя.
 *
 * @author Iurii Babalin.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Получение пользователя.
     *
     * @param email почта.
     * @return {@link Customer}.
     */
    Customer getCustomerByEmail(String email);
}