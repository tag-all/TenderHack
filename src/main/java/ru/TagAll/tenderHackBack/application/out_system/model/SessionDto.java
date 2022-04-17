package ru.TagAll.tenderHackBack.application.out_system.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Модель сессии
 */
@Data
public class SessionDto {
    private Long id;
    private String sessionName;
    private String customerName;
    private Double currentPrice;
    private LocalDateTime start;
    private LocalDateTime end;
    private Double bet;
    private String location;
    private String lastCustomerBet;
    private String status;
}
