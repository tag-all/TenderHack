package ru.TagAll.tenderHackBack.application.bot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

import java.util.List;


/**
 * Репозиторий для взаимодействия c таблицей статус сессии.
 *
 * @author Iurii Babalin.
 */

public interface StatusSessionRepository extends JpaRepository<StatusSession, Long> {
    StatusSession getByCustomerAndSessionId(Customer customer, Long sessionId);

    List<StatusSession> getAllByCustomerAndAndOperatingMode(Customer customer, boolean status);

    List<StatusSession> findAllByOperatingModeAndSessionId(boolean status, Long sessionId);

    List<StatusSession> findAllBySessionId(Long sessionId);

}
