package ru.TagAll.tenderHackBack.application.out_system.service;

import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;

/**
 * Получение сессий из внешней системы
 *
 * @author Iurii Babalin.
 */
public interface OutSystemService {

    /**
     * Получение сессии по типу
     * @param sessionType - тип сессии
     * @param customerKey - ключ пользователя
     * @return модель сесиии
     */
    SessionsDto getSessionByType(String sessionType, String customerKey);

    /**
     * Получение сессии по id
     * @param sessionId - айдишник сессии
     * @return модель сесиии
     */
    SessionDto getSessionById(Long sessionId);


    /**
     * Поставить ставку в конкретной сессии
     * @param sessionId - айдишник сессии
     * @param customerKey - ключ пользователя
     */
    void betToSession(Long sessionId, String customerKey);
}
