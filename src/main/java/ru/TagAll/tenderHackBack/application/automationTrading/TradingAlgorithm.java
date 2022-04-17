package ru.TagAll.tenderHackBack.application.automationTrading;

/**
 * алгоритмы для автоматизация трейдинга
 *
 * @author Semyon Shibaev.
 */

public interface TradingAlgorithm {

    /**
     * В последние 5 минута трейдинга начинается особенно напряженное соревнование за ставку.
     * Но так как с каждой ставкой мы приближаемся к абсолютному минимуму для конкретного пользователя,
     * то следует считать время для ставки по особым формулам.
     */

    /**
     * @param currentBet - текущая ставка
     * @param minimumUserBet - минимальная ставка, на которую согласен текущий пользователь
     * @param priority - приоритет сессии для пользователя
     * @return время в секундах, через которую нужно сделать следующую ставку
     */

    double getDelayForNextBetInLastFiveMinutes(double currentBet, double minimumUserBet, Priority priority);

}
