package ru.TagAll.tenderHackBack.application.notification.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;
import ru.TagAll.tenderHackBack.application.notification.servcie.impl.NotificationServiceImpl;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static ru.TagAll.tenderHackBack.utils.ParsingUtils.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер уведомлений")
public class NotificationController {

    private final NotificationServiceImpl noticeService;

    @BadRequestSystemError
    @GetMapping(value = Endpoints.Notice.NOTICES_BY_TYPE)
    public List<NotificationDto> getNoticeByType(HttpServletRequest request){
        String url = request.getRequestURI();
        Long customerId = Long.valueOf(parseUrlParameter(url, 1));
        String notificationType = parseUrlParameter(url,3);
        return noticeService.getNoticeByCustomerIdAndType(customerId,notificationType);
    }

    @BadRequestSystemError
    @GetMapping(value = Endpoints.Notice.COUNTING_NOTICES)
    public int getCountNoticeByType(HttpServletRequest request){
        String url = request.getRequestURI();
        Long customerId = Long.valueOf(parseUrlParameter(url, 1));
        String notificationType = parseUrlParameter(url,3);
        return noticeService.getCountTypedNotice(customerId,notificationType);
    }


}
