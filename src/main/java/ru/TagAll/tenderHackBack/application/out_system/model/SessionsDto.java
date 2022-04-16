package ru.TagAll.tenderHackBack.application.out_system.model;

import lombok.Data;

import java.util.List;

@Data
public class SessionsDto {
    private List<SessionDto> sessions;
}
