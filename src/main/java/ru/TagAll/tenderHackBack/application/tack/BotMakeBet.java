package ru.TagAll.tenderHackBack.application.tack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.bot.domain.StatusSessionRepository;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

import java.time.Duration;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class BotMakeBet {

    private final StatusSessionRepository statusSessionRepository;

    private final BotSettingsRepository botSettingsRepository;

    private final OutSystemService outSystemService;

    @Scheduled(cron = "0/3 * * * * *")
    public void makeBet() {
        botSettingsRepository.getFullActiveSession().forEach(it -> {
            statusSessionRepository.findAllBySessionId(it).forEach(is -> {
                if (is.getOperatingMode()) {
                    long h = Long.parseLong(is.getBotSettings().getTimeDelay().toString().split(":")[0]);
                    long m = Long.parseLong(is.getBotSettings().getTimeDelay().toString().split(":")[1]);
                    long s = Long.parseLong(is.getBotSettings().getTimeDelay().toString().split(":")[2]);
                    long sec = s + h * 60 * 60 + m * 60;
                    long secCheck = Duration.between(is.getBotSettings().getTimeStart().toLocalTime(), LocalTime.now())
                            .toNanos() / 1000000000;
                    if (secCheck >= sec) {
                        SessionDto sessionDto = outSystemService.getSessionById(it);
                        if (sessionDto.getCurrentPrice() >= is.getBotSettings().getMinPayment()
                                && !sessionDto.getLastCustomerBet().equals(is.getCustomer().getCompanyName())
                                && !sessionDto.getStatus().equals("CLOSE")) {
                            BotSettings updateBotSettings = is.getBotSettings();
                            updateBotSettings.setTimeStart(ConvertorUtils.getTimeNot());
                            is.setBotSettings(updateBotSettings);
                            statusSessionRepository.save(is);
                            outSystemService.betToSession(it, is.getCustomer().getAccessKey());
                        }
                    }
                }
            });
        });
    }
}
