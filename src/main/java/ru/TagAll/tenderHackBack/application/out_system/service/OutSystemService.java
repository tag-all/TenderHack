package ru.TagAll.tenderHackBack.application.out_system.service;

import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;

public interface OutSystemService {
    SessionsDto getSessionByType(String sessionType, String customerKey);

    SessionDto getSessionById(Long sessionId);

    void betToSession(Long sessionId, String customerKey);
}
