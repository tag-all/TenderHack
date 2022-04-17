package ru.TagAll.tenderHackBack.application.customer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettings;
import ru.TagAll.tenderHackBack.application.bot.domain.BotSettingsRepository;
import ru.TagAll.tenderHackBack.application.bot.domain.StatusSession;
import ru.TagAll.tenderHackBack.application.bot.domain.StatusSessionRepository;
import ru.TagAll.tenderHackBack.application.bot.model.BotSettingDto;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.model.SessionAuto;
import ru.TagAll.tenderHackBack.application.customer.model.SessionsAuto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

import java.sql.Time;
import java.util.stream.Collectors;


/**
 * Логика работы с пользователем.
 *
 * @author Iurii Babalin.
 * @author Semyon Shibaev.
 */
@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /**
     * {@link CustomerRepository}.
     */
    private final CustomerRepository customerRepository;

    private final OutSystemService outSystemService;

    private final StatusSessionRepository statusSessionRepository;

    private final BotSettingsRepository botSettingsRepository;

    /**
     * Получение данных опользователе.
     *
     * @return {@link CustomerDto}.
     */
    @Override
    public CustomerDto getCustomer() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ConvertorUtils.convertCustomerToCustomerDto(customer);
    }

    @Override
    public SessionsDto getAllActiveSessions() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return outSystemService.getSessionByType("ACTIVE", customer.getAccessKey());
    }

    @Override
    public SessionsDto getAllManualSessions() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SessionsDto sessionsDto = new SessionsDto();
        sessionsDto.setSessions(statusSessionRepository.getAllByCustomerAndAndOperatingMode(customer, false)
                .stream().map(it -> outSystemService.getSessionById(it.getSessionId()))
                .collect(Collectors.toList()));
        return sessionsDto;
    }

    @Override
    public SessionsAuto getAllAutoSessions() {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SessionsAuto sessionsAuto = new SessionsAuto();
        sessionsAuto.setSessions(statusSessionRepository.getAllByCustomerAndAndOperatingMode(customer, true)
                .stream().map(it -> {
                    SessionAuto sessionAuto = convertToSessionAuto(outSystemService.getSessionById(it.getSessionId()));
                    sessionAuto.setBotSettingDto(convertToBotSettingDto(botSettingsRepository.getByStatusSession(it)));
                    return sessionAuto;
                })
                .collect(Collectors.toList()));
        return sessionsAuto;
    }

    @Override
    public SessionDto getSessionInfo(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return outSystemService.getSessionById(sessionId);
    }

    @Override
    public void placeManualBet(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SessionDto sessionDto = outSystemService.getSessionById(sessionId);
        StatusSession statusSession = new StatusSession();
        statusSession.setCustomer(customer);
        statusSession.setSessionId(sessionId);
        statusSession.setStatus(sessionDto.getStatus());
        statusSession.setOperatingMode(false);
        statusSessionRepository.save(statusSession);
        outSystemService.betToSession(sessionId, customer.getAccessKey());
    }

    @Override
    public void updateProfile(CustomerDto customerDto) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customerForSave = customerRepository.getCustomerByEmail(customer.getEmail());
        customerForSave.setCompanyName(customerDto.getCompanyName());
        customerForSave.setEmail(customerDto.getEmail());
        customerForSave.setAccessKey(customerDto.getAccessKey());
        customerForSave.setNotificationTime(Time.valueOf(customerDto.getNotificationDelay()));
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(customerForSave,
                null, null));
        customerRepository.save(customerForSave);
    }

    private BotSettingDto convertToBotSettingDto(BotSettings botSettings) {
        BotSettingDto botSettingDto = new BotSettingDto();
        botSettingDto.setId(botSettings.getId());
        botSettingDto.setPriority(botSettings.getPriority());
        botSettingDto.setTimeDelay(String.valueOf(botSettings.getTimeDelay()));
        botSettingDto.setMinPay(botSettings.getMinPayment());
        return botSettingDto;
    }

    private SessionAuto convertToSessionAuto(SessionDto sessionDto){
        SessionAuto sessionAuto = new SessionAuto();
        sessionAuto.setId(sessionDto.getId());
        sessionAuto.setSessionName(sessionDto.getSessionName());
        sessionAuto.setCustomerName(sessionDto.getCustomerName());
        sessionAuto.setCurrentPrice(sessionDto.getCurrentPrice());
        sessionAuto.setStart(sessionDto.getStart());
        sessionAuto.setEnd(sessionDto.getEnd());
        sessionAuto.setBet(sessionDto.getBet());
        sessionAuto.setLocation(sessionDto.getLocation());
        sessionAuto.setLastCustomerBet(sessionDto.getLastCustomerBet());
        sessionAuto.setStatus(sessionDto.getStatus());
        return sessionAuto;
    }
}
