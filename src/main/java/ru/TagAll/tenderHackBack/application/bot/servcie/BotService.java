package ru.TagAll.tenderHackBack.application.bot.servcie;

import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.bot.model.DelayDto;

/**
 * @author Semyon Shibaev.
 */
public interface BotService {

    /**
     * запуск бота для конкретной сессии
     */
    void startBot(Long sessionId);

    /**
     * остановка бота для конкретной сессии
     */
    void stopBot(Long sessionId);

    /**
     * сохранение настроек бота для конкретной сессии
     */
    void settingBotSave(Long sessionId, BotSettingDto setting);

    /**
     * сохранение настроек бота для конкретной сессии
     */
    BotSettingDto settingBotGet(Long sessionId);

}
