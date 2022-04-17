package ru.TagAll.tenderHackBack.application.automationTrading.impl;

import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.automationTrading.TradingAlgorithm;

import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * @author Semyon Shibaev.
 */
@Service
public class TradingAlgorithmImpl implements TradingAlgorithm {

    private final int MAXIMUM_TIME_BETWEEN_BETS_IN_SECOND = 290;
    private final int MINIMUM_TIME_BETWEEN_BETS_IN_SECOND = 4;
    private final double COEFFICIENT_FOR_HIGH_PRIORITY = 1;
    private final double COEFFICIENT_FOR_MIDDLE_PRIORITY = 1.5;
    private final double COEFFICIENT_FOR_LOW_PRIORITY = 2;
    private final double COEFFICIENT_FOR_NO_PRIORITY = 3;


    /**
     * в данной реализации для достижении наилучшего эффекта используем
     * экспотенциальную нашу формулу успеха:
     * 0.5 * e^(-0.05 * (100 - minPrice/currentPrice * 100)+5)
     *
     * @return задержку в секундах
     */

    @Override
    public long getDelayForNextBetInLastFiveMinutes(double currentBet, double minimumUserBet, long priority) {
        int result = (int) round(0.5 * pow(Math.E, (-0.05 * (100 - minimumUserBet / currentBet * 100) + 5)));

        if (priority == 1)
            result = (int) round(result * COEFFICIENT_FOR_HIGH_PRIORITY);

        if (priority == 2)
            result = (int) round(result * COEFFICIENT_FOR_MIDDLE_PRIORITY);

        if (priority == 3)
            result = (int) round(result * COEFFICIENT_FOR_LOW_PRIORITY);

        if (priority == 0)
            result = (int) round(result * COEFFICIENT_FOR_NO_PRIORITY);

        if (result < MINIMUM_TIME_BETWEEN_BETS_IN_SECOND)
            return MINIMUM_TIME_BETWEEN_BETS_IN_SECOND;

        if (result > MAXIMUM_TIME_BETWEEN_BETS_IN_SECOND)
            return MINIMUM_TIME_BETWEEN_BETS_IN_SECOND;

        return result;

    }


}
