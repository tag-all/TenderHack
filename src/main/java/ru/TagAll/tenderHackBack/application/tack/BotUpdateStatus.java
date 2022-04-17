package ru.TagAll.tenderHackBack.application.tack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.automationTrading.TradingAlgorithm;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.bot.domain.StatusSessionRepository;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class BotUpdateStatus {

    private final StatusSessionRepository statusSessionRepository;

    private final BotSettingsRepository botSettingsRepository;

    private final OutSystemService outSystemService;

    private final TradingAlgorithm tradingAlgorithm;

    /**
     * Обновить данные для бота
     */
    @Scheduled(cron = "0/1 * * * * *")
    public void updateDelay() {
        botSettingsRepository.getFullActiveSession().forEach(it -> {
            SessionDto sessionDto = outSystemService.getSessionById(it);
            long secCheck = Duration.between(LocalTime.now(), sessionDto.getEnd().toLocalTime())
                    .toNanos() / 1000000000;
            if (secCheck <= 300) {
                statusSessionRepository.findAllByOperatingModeAndSessionId(true, it).forEach(is -> {
                    BotSettings updateBotSettings = is.getBotSettings();
                    long seconds = tradingAlgorithm.getDelayForNextBetInLastFiveMinutes(sessionDto.getCurrentPrice(),
                            updateBotSettings.getMinPayment(), updateBotSettings.getPriority());
                    int day = (int) TimeUnit.SECONDS.toDays(seconds);
                    long hours = TimeUnit.SECONDS.toHours(seconds) - (day * 24L);
                    long minute = TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
                    long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);
                    Time setTime = Time.valueOf(String.valueOf(hours).concat(":")
                            .concat(String.valueOf(minute)).concat(":")
                            .concat(String.valueOf(second)));
                    if (!Objects.equals(updateBotSettings.getTimeDelay(), setTime)) {
                        updateBotSettings.setTimeDelay(setTime);
                        updateBotSettings.setTimeStart(ConvertorUtils.getTimeNot());
                        is.setBotSettings(updateBotSettings);
                        statusSessionRepository.save(is);
                    }
                });
            }
        });
    }
}
