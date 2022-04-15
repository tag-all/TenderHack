package ru.TagAll.tenderHackBack.application.common;

/**
 * Эндпоинты приложения для взаимодействия.
 *
 * @author Iurii Babalin.
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
}

