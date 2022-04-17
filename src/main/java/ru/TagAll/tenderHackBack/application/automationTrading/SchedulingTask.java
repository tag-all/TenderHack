package ru.TagAll.tenderHackBack.application.automationTrading;


import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;

import java.util.ArrayList;
import java.util.List;

/**
 * задаем, что именно крутить в scheduling
 * @author Semyon Shibaev.
 */

@Component
@AllArgsConstructor
public class SchedulingTask {

    private final BotSettingsRepository botRepository;
    private final OutSystemService outSystemService;

    /**
     * каждые 4 секунды проверяем не пришла ли очередь бота,
     * если пришла, то обновляем
     */

    @Scheduled(cron = "4 * * * * *")
    public void checkBets(){
        List<Integer> activeSession = botRepository.getAllActiveSession();
        for(Integer i: activeSession){
            List<BotSettings> bots = botRepository.getAllBySessionId(Long.valueOf(i));
            for(BotSettings bot: bots){
                if(bot.getMinPayment() <= outSystemService.(i,).);





            }
        }

    }


    private void updateBet(){

    }


}
