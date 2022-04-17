package ru.TagAll.tenderHackBack.application.tack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.bot.domain.StatusSessionRepository;
import ru.TagAll.tenderHackBack.application.mailSender.service.MailSender;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;

import javax.mail.MessagingException;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class UpdateStatusSession {

    private final OutSystemService outSystemService;

    public final StatusSessionRepository statusSessionRepository;

    private final BotSettingsRepository botSettingsRepository;

    private final MailSender mailSender;

    @Scheduled(cron = "0/2 * * * * *")
    public void updateStatusAndSendNotification() {
        botSettingsRepository.getFullActiveSession().forEach(it -> {
            SessionDto sessionDto = outSystemService.getSessionById(it);
            statusSessionRepository.findAllBySessionId(it).forEach(is -> {
                is.setStatus(sessionDto.getStatus());
                if (sessionDto.getStatus().equals("CLOSE")) {
                    try {
                        mailSender.sendMail(is.getCustomer().getEmail(),
                                "Сессия завершена, финальная цена " + sessionDto.getCurrentPrice());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
                statusSessionRepository.save(is);
            });
        });
    }
}
