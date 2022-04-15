package ru.TagAll.tenderHackBack.application.customer.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer getCustomerByEmail(String email);
}
