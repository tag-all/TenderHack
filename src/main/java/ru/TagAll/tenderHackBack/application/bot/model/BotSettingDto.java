package ru.TagAll.tenderHackBack.application.bot.model;

import lombok.Data;

@Data
public class BotSettingDto {
    private Long id;
    private Long priority;
    private String timeDelay;
    private Double minPay;
}
