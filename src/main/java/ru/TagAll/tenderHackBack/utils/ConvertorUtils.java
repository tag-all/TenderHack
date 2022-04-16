package ru.TagAll.tenderHackBack.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.TagAll.tenderHackBack.application.auth.model.RegistrationDto;
import ru.TagAll.tenderHackBack.application.customer.domain.Customer;
import ru.TagAll.tenderHackBack.application.customer.model.CustomerDto;
import ru.TagAll.tenderHackBack.application.notification.domain.Notification;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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

    public static List<NotificationDto> convertNoticeToNoticeDto(List<Notification> notices ) {
        List<NotificationDto> result = new ArrayList<>();
        for (Notification notification : notices) {
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setId(String.valueOf(notification.getCustomer().getId()));
            notificationDto.setMessage(notification.getMessage().getText());
            notificationDto.setType(String.valueOf(notification.getMessage().getType()));
            result.add(notificationDto);
        }
        return result;
    }

    public static CustomerDto convertCustomerToCustomerDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(customer.getEmail());
        customerDto.setCompanyName(customer.getCompanyName());
        customerDto.setNotificationDelay(customer.getNotificationTime().toString());
        return customerDto;
    }
}
