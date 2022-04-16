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
    interface Notice {
        String NOTICES_BY_TYPE = "/customer/notification/{notificationType}";
        String COUNTING_NOTICES = "/customer/notification/{notificationType}/count";
    }

    /**
     *  Эндпоинты взаимодействия с сервисом взаиомдействия с системой автоматизации.
     */
    interface Bot{
        String START_BOT = "/bot/session/{sessionId}/start";
        String STOP_BOT = "/bot/session/{sessionId}/stop";
        String CHANGE_DELAY = "/bot/session/{sessionId}/profile/update";
        String SETTING_BOT_SAVE = "bot/session/{sessionId}/settings/save";
        String SETTING_BOT_GET = "bot/session/{sessionId}/settings";
    }

    /**
     *  Эндпоинты взаимодействия с сервисом пользователя.
     */
    interface Customer{
        String GET_CUSTOMER_PROFILE = "/customer/profile";
        String PROFILE_UPDATE = "/customer/profile/save";
        String AUTO_SESSIONS = "/customer/auto/sessions";
        String MANUAL_SESSIONS = "/customer/manual/sessions";
        String ACTIVE_SESSIONS = "customer/session/active";
        String SESSION_INFO = "customer/session/{sessionId}";
        String PLACE_BET = "customer/session/{sessionId}/bet";
    }


}

