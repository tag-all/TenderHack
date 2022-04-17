package ru.TagAll.tenderHackBack.application.automationTrading;

/**
 * подсчет времени для конкретных приоритетов ( не в последние 5 минут )
 *
 * @author Semyon Shibaev.
 */


public class CountingTime {

    /**
     * при подсчете учитываем общее время сессии в секундах
     *
     *@param seconds - общее время сессии в секундах
     *@param sessionStep - шаг для ставки в %
     *@return время в секундах для ставкок
     */

    /**
     * подсчет для высокого приоритета
     */

    public static int getTimeForHighPri(int seconds, double sessionStep) {
        double sumSteps = 100 / sessionStep;
        return (int) Math.round(seconds / sumSteps);
    }

    /**
     * подсчет для среднего приоритета
     */
    public static int getTimeForMiddlePri(int seconds, double sessionStep) {
        double sumSteps = 100 / sessionStep;
        return (int) Math.round(seconds / sumSteps) * 2;
    }

    ;

    /**
     * подсчет для низкого приоритета
     */
    public static int getTimeForLowPri(int seconds, double sessionStep) {
        double sumSteps = 100 / sessionStep;
        return (int) Math.round(seconds / sumSteps) * 3;
    }


    /**
     * подсчет минимального времени при отсутствии приоритета - минимальное время, но пользователь
     * может выбрать время еще больше
     */
    public static int getMinimumTimeForNoPri(int seconds, double sessionStep) {
        double sumSteps = 100 / sessionStep;
        return (int) Math.round(seconds / sumSteps) * 4;
    }

}
