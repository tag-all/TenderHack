package ru.TagAll.tenderHackBack.application.common;

/**
 * Эндпоинты приложения для взаимодействия.
 *
 * @author Iurii Babalin.
 * @author Semyon Shibaev.
 */
public interface Endpoints {
    /**
     *  Эндпоинты взаимодействия с сервисом авторизации.
     */
    interface Auth{
        String AUTH = "/auth/login";
        String REGISTRATION ="/auth/registration";
        String LOGOUT = "/auth/logout";
        String UPDATE_PASSWORD = "/auth/update/password";
        String UPDATE_TOKEN = "/auth/update/token";
    }

    /**
     *  Эндпоинты взаимодействия с сервисом отправки уведомлений.
     */
    interface Notice{
        String NOTICES_BY_TYPE = "/customer/{customerId}/notification/{notificationType}";
        String COUNTING_NOTICES = "/customer/{customerId}/notification/{notificationType}/count";
    }
}

