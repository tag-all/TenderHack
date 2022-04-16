package ru.TagAll.tenderHackBack.application.bot.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ИНтерфейс работы с настройками бота.
 *
 * @author Iurii Babalin.
 */
public interface BotSettingsRepository extends JpaRepository<BotSettings, Long> {
    BotSettings getByStatusSession(StatusSession statusSession);
}
