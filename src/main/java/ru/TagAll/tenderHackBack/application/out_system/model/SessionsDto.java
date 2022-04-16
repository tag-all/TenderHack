package ru.TagAll.tenderHackBack.application.out_system.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class SessionsDto {
    private List<SessionDto> sessions;
}
