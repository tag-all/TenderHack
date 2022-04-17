package ru.TagAll.tenderHackBack.application.bot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

import java.util.List;

public interface StatusSessionRepository extends JpaRepository<StatusSession, Long> {
    StatusSession getByCustomerAndSessionId(Customer customer, Long sessionId);

    List<StatusSession> getAllByCustomerAndAndOperatingMode(Customer customer, boolean status);

    List<StatusSession> findAllByOperatingModeAndSessionId(boolean status, Long sessionId);

    List<StatusSession> findAllBySessionId(Long sessionId);

}
