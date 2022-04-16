package ru.TagAll.tenderHackBack.application.customer.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.domain.CustomerRepository;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.customer.model.SessionDto;
import ru.TagAll.tenderHackBack.application.customer.service.CustomerService;
import ru.TagAll.tenderHackBack.utils.ConvertorUtils;

import java.sql.Time;
import java.util.List;


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
    public List<SessionDto> getAllActiveSessions() {
        return null;
    }

    @Override
    public List<SessionDto> getAllManualSessions() {
        return null;
    }

    @Override
    public List<SessionDto> getAllAutoSessions() {
        return null;
    }

    @Override
    public SessionDto getSessionInfo(Long sessionID) {
        return null;
    }

    @Override
    public void placeManualBet(Long sessionID) {
    }

    @Override
    public void updateProfile(CustomerDto customerDto) {
        Customer customer = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customerForSave = customerRepository.getCustomerByEmail(customer.getEmail());
        customerForSave.setCompanyName(customerDto.getCompanyName());
        customerForSave.setEmail(customerDto.getEmail());
        customerForSave.setAccessKey(customerDto.getAccessKey());
        customerForSave.setNotificationTime(Time.valueOf(customerDto.getNotificationDelay()));
        customerRepository.save(customerForSave);
    }
}
