package ru.TagAll.tenderHackBack.application.bot.model;

import lombok.Data;


/**
 * Модель для конкретного бота конкретного пользователя
 *
 * @author Iurii Babalin.
 */

@Data
public class BotSettingDto {
    private Long id;
    private Long priority;
    private String timeDelay;
    private Double minPay;
}
