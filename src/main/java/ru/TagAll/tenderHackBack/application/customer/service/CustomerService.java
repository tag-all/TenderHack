package ru.TagAll.tenderHackBack.application.customer.service;

import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.model.SessionsAuto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;


/**
 * Сеовис работы с пльзователем.
 *
 * @author Semyon Shibaev.
 * @author Iurii Babalin.
 */
public interface CustomerService {
    /**
     * Получение данных о пользователе.
     *
     * @return {@link CustomerDto}.
     */
    CustomerDto getCustomer();

    /**
     * @return активные сессии
     */
    SessionsDto getAllActiveSessions();

    /**
     * @return сессии в ручном режиме
     */
    SessionsDto getAllManualSessions();

    /**
     * @return сессии в автоматическом режиме
     */
    SessionsAuto getAllAutoSessions();

    /**
     * @param sessionId - id сессии
     * @return инфрормацию о сессии
     */
    SessionDto getSessionInfo(Long sessionId);

    /**
     * @param sessionId - id сессии
     * ставим ручную ставку
     */
    void placeManualBet(Long sessionId);

    /**
     * Обновление профиля пользователя.
     */
    void updateProfile(CustomerDto customerDto);

}
