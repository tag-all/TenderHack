package ru.TagAll.tenderHackBack.application.bot.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.bot.servcie.BotService;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;



/**
 * @author Semyon Shibaev.
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер натсройки бота")
public class BotController {

    private final BotService botService;

    /**
     * запуск бота для конкретной сессии
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Bot.START_BOT)
    public void startBot(@PathVariable Long sessionId) {
        botService.startBot(sessionId);
    }

    /**
     * остановка бота для конкретной сессии
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Bot.STOP_BOT)
    public void stopBot(@PathVariable Long sessionId) {
        botService.stopBot(sessionId);
    }

    /**
     * сохранение настроек бота для конкретной сессии
     */
    @BadRequestSystemError
    @PostMapping(value = Endpoints.Bot.SETTING_BOT_SAVE)
    public void settingBotSave(@PathVariable Long sessionId, @RequestBody BotSettingDto botSettingDto) {
        botService.settingBotSave(sessionId, botSettingDto);
    }

    /**
     * сохранение настроек бота для конкретной сессии
     */
    @BadRequestSystemError
    @GetMapping(value = Endpoints.Bot.SETTING_BOT_GET)
    public BotSettingDto settingBotGet(@PathVariable Long sessionId) {
        return botService.settingBotGet(sessionId);
    }
    

}
