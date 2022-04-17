package ru.TagAll.tenderHackBack.application.customer.model;

import lombok.Data;

import java.util.List;

/**
 * Модель сессий.
 */
@Data
public class SessionsAuto {
    private List<SessionAuto> sessions;
}
