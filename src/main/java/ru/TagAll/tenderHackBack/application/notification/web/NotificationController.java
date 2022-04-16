package ru.TagAll.tenderHackBack.application.notification.web;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.TagAll.tenderHackBack.application.common.Endpoints;
import ru.TagAll.tenderHackBack.application.notification.model.NotificationDto;
import ru.TagAll.tenderHackBack.application.notification.servcie.impl.NotificationServiceImpl;
import ru.TagAll.tenderHackBack.swagger.BadRequestSystemError;

import javax.servlet.http.HttpServletRequest;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = "Контроллер уведомлений")
public class NotificationController {

    private final NotificationServiceImpl noticeService;

    @BadRequestSystemError
    @GetMapping(value = Endpoints.Notice.NOTICES_BY_TYPE)
    public List<NotificationDto> getNoticeByType(@PathVariable String notificationType){
        return noticeService.getNoticeByType(notificationType);
    }

    @BadRequestSystemError
    @GetMapping(value = Endpoints.Notice.COUNTING_NOTICES)
    public int getCountNoticeByType(@PathVariable String notificationType){
        return noticeService.getCountTypedNotice(notificationType);
    }


}
