package ru.TagAll.tenderHackBack.application.bot.servcie.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.bot.servcie.BotService;

/**
 * Реализация настроек бота.
 *
 * @author Iurii Babalin.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BotServiceImpl implements BotService {


    @Override
    public void startBot(Long sessionId) {

    }

    @Override
    public void stopBot(Long sessionId) {

    }

    @Override
    public void changeDelay(Long sessionId) {

    }

    @Override
    public void settingBotSave(Long sessionId, BotSettingDto setting) {

    }

    @Override
    public BotSettingDto settingBotGet(Long sessionId) {
        return null;
    }
}
