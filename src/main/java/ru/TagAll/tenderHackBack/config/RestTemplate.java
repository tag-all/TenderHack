package ru.TagAll.tenderHackBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration
@ComponentScan(basePackages = "ru.TagAll.tenderHackBack")
public class RestTemplate {
    /**
     * Создание шаблона для запросов.
     *
     * @return шаблон.
     */
    @Bean
    public org.springframework.web.client.RestTemplate captchaRestTemplate() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(30000);
        clientHttpRequestFactory.setReadTimeout(30000);
        return new org.springframework.web.client.RestTemplate(clientHttpRequestFactory);
    }
}
