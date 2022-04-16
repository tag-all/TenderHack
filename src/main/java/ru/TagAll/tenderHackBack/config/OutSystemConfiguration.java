package ru.TagAll.tenderHackBack.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@Slf4j
@Validated
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "out-system")
public class OutSystemConfiguration {

    private String url;

    private String key;

    private Endpoints endpoints;

    @Data
    public static class Endpoints{
        @NotBlank(message = "Отсутствует значение параметра out-system.endpoints.get-sessions")
        private String getSessions;
        @NotBlank(message = "Отсутствует значение параметра out-system.endpoints.get-session")
        private String getSession;
        @NotBlank(message = "Отсутствует значение параметра out-system.endpoints.make-bet")
        private String makeBet;
    }
}
