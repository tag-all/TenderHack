package ru.TagAll.tenderHackBack.application.bot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * ИНтерфейс работы с настройками бота.
 *
 * @author Iurii Babalin.
 */
public interface BotSettingsRepository extends JpaRepository<BotSettings, Long> {
    BotSettings getByCustomerIdAndSessionId(Long customerId, Long sessionId);

    List<BotSettings> getAllBySessionId(Long sessionId);

    @Query(value = "select session_id from bot_settings join status_session " +
            "on bot_settings.status_session_id = status_session.id " +
            "where status_session.status like 'ACTIVE' DISTINCT", nativeQuery = true)
    List<Integer> getAllActiveSession();
    BotSettings getByStatusSession(StatusSession statusSession);
}
