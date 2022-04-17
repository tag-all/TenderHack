package ru.TagAll.tenderHackBack.application.bot.model;

import lombok.Data;


/**
 * Модель для задержки - через какое время будет сделана попытка поставить ставку
 *
 * @author Iurii Babalin.
 */
@Data
public class DelayDto {
    private String delay;
}
