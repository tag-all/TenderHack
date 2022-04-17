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

    @Query(value = "select distinct session_id from bot_settings join status_session " +
            "on bot_settings.status_session_id = status_session.id " +
            "where status_session.status = 'ACTIVE'", nativeQuery = true)
    List<Long> getFullActiveSession();

    BotSettings getByStatusSession(StatusSession statusSession);

}
