package ru.TagAll.tenderHackBack.application.bot.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.bot.servcie.BotService;

@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер натсройки бота")
public class BotController {

    /**
     * {@link BotService}.
     */
    private final BotService botService;
}
