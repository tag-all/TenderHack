package ru.TagAll.tenderHackBack.application.customer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

import java.sql.Time;


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
        return null;
    }

    @Override
    public SessionsDto getAllAutoSessions() {
        return null;
    }

    @Override
    public SessionDto getSessionInfo(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return outSystemService.getSessionById(sessionId, customer.getAccessKey());
    }

    @Override
    public void placeManualBet(Long sessionId) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
}
