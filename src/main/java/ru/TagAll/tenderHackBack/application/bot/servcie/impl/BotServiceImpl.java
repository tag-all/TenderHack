package ru.TagAll.tenderHackBack.application.bot.servcie.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.bot.servcie.BotService;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.errors.ErrorDescription;

import java.sql.Time;

/**
 * Реализация настроек бота.
 *
 * @author Iurii Babalin.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BotServiceImpl implements BotService {

    private final BotSettingsRepository botSettingsRepository;

    private final OutSystemService outSystemService;

    @Override
    public void startBot(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BotSettings botSettings = botSettingsRepository.getByCustomerIdAndSessionId(customer.getId(), sessionId);
        ErrorDescription.BOT_NOT_FOUNT.throwIfTrue(ObjectUtils.isEmpty(botSettings));
        botSettings.setStatusWork(true);
        botSettingsRepository.save(botSettings);
    }

    @Override
    public void stopBot(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BotSettings botSettings = botSettingsRepository.getByCustomerIdAndSessionId(customer.getId(), sessionId);
        ErrorDescription.BOT_NOT_FOUNT.throwIfTrue(ObjectUtils.isEmpty(botSettings));
        botSettings.setStatusWork(false);
        botSettingsRepository.save(botSettings);
    }

    @Override
    public void settingBotSave(Long sessionId, BotSettingDto setting) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BotSettings botSettings = botSettingsRepository.getByCustomerIdAndSessionId(customer.getId(), sessionId);
        if (ObjectUtils.isEmpty(botSettings)) {
            ErrorDescription.BOT_NOT_FOUNT.throwIfTrue(!ObjectUtils.isEmpty(setting.getId()));
            botSettings = new BotSettings();
            botSettings.setCustomer(customer);
            botSettings.setSessionId(sessionId);
            botSettings.setStatusWork(true);
            botSettings.setStep(outSystemService.getSessionById(sessionId, customer.getAccessKey()).getBet());
            botSettings.setPriority(setting.getPriority());
            botSettings.setMinPayment(setting.getMinPay());
            botSettings.setTimeDelay(Time.valueOf(setting.getTimeDelay()));
            botSettingsRepository.save(botSettings);
        } else {
            ErrorDescription.BOT_NOT_FOUNT.throwIfTrue(!botSettings.getId().equals(setting.getId()));
            botSettings.setPriority(setting.getPriority());
            botSettings.setMinPayment(setting.getMinPay());
            botSettings.setTimeDelay(Time.valueOf(setting.getTimeDelay()));
            botSettingsRepository.save(botSettings);
        }
    }

    @Override
    public BotSettingDto settingBotGet(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return convertToBotSettingDto(botSettingsRepository.getByCustomerIdAndSessionId(customer.getId(), sessionId));
    }

    private BotSettingDto convertToBotSettingDto(BotSettings botSettings){
        BotSettingDto botSettingDto = new BotSettingDto();
        botSettingDto.setId(botSettings.getId());
        botSettingDto.setPriority(botSettings.getPriority());
        botSettingDto.setTimeDelay(String.valueOf(botSettings.getTimeDelay()));
        botSettingDto.setMinPay(botSettings.getMinPayment());
        return botSettingDto;
    }
}
