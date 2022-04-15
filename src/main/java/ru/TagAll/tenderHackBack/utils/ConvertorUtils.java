package ru.TagAll.tenderHackBack.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

import java.sql.Time;

public class ConvertorUtils {
    public static Customer convertRegistrationDtoToCustomer(RegistrationDto registrationDto) {
        Customer customer = new Customer();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        customer.setEmail(registrationDto.getLogin());
        customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        customer.setCompanyName(registrationDto.getCompanyName());
        customer.setNotificationTime(Time.valueOf("01:00:00"));
        return customer;
    }
}
