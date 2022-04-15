package ru.TagAll.tenderHackBack.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.common.ApplicationRoles;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;

public class ConvertorUtils {
    public static Customer convertRegistrationDtoToCustomer(RegistrationDto registrationDto) {
        Customer customer = new Customer();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        customer.setEmail(registrationDto.getLogin());
        customer.setName(registrationDto.getName());
        customer.setLastName(registrationDto.getLastName());
        customer.setPatronymic(registrationDto.getPatronymic());
        customer.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        if (!registrationDto.getPhone().isEmpty()) {
            customer.setPhone(registrationDto.getPhone().substring(registrationDto.getPhone().length() - 10));
            customer.setCodeRegion(registrationDto.getPhone().substring(0, registrationDto.getPhone().length() - 10));
        }
        customer.setRole(ApplicationRoles.USER);

        return customer;
    }
}
