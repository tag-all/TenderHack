package ru.TagAll.tenderHackBack.application.bot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

public interface StatusSessionRepository extends JpaRepository<StatusSession, Long> {
    StatusSession getByCustomerAndSessionId(Customer customer, Long sessionId);
}
