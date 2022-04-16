package ru.TagAll.tenderHackBack.application.customer.service;

import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.model.SessionDto;

import java.util.List;



/**
 * Сеовис работы с пльзователем.
 *
 * @author Semyon Shibaev.
 * @author Iurii Babalin.
 */
public interface CustomerService {
    /**
     * Получение данных опользователе.
     *
     * @return {@link CustomerDto}.
     */
    CustomerDto getCustomer();

    /**
     * @return активные сессии
     */
    List<SessionDto> getAllActiveSessions();

    /**
     * @return сессии в ручном режиме
     */
    List<SessionDto> getAllManualSessions();

    /**
     * @return сессии в автоматическом режиме
     */
    List<SessionDto> getAllAutoSessions();

    /**
     * @param sessionID - id сессии
     * @return инфрормацию о сессии
     */
    SessionDto getSessionInfo(Long sessionID);

    /**
     * @param sessionID - id сессии
     * @return реазультат - удалась ли ставка
     */
    void placeManualBet(Long sessionID);


}
