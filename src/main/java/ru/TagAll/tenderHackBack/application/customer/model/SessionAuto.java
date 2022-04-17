package ru.TagAll.tenderHackBack.application.customer.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;

/**
 * Модель сессии.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SessionAuto extends SessionDto {
    private BotSettingDto botSettingDto;
}
