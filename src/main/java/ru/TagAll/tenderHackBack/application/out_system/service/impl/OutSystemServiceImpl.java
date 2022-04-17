package ru.TagAll.tenderHackBack.application.out_system.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionDto;
import ru.TagAll.tenderHackBack.application.out_system.model.SessionsDto;
import ru.TagAll.tenderHackBack.application.out_system.service.OutSystemService;
import ru.TagAll.tenderHackBack.config.OutSystemConfiguration;
import ru.TagAll.tenderHackBack.errors.ErrorDescription;

@Slf4j
@Service
@AllArgsConstructor
public class OutSystemServiceImpl implements OutSystemService {
    /**
     * {@link RestTemplate}.
     */
    private final RestTemplate outSystemRestTemplate;

    /**
     * {@link RestTemplate}.
     */
    private final OutSystemConfiguration outSystemConfiguration;

    @Override
    public SessionsDto getSessionByType(String sessionType, String customerKey) {
        String url = outSystemConfiguration.getUrl().concat(outSystemConfiguration.getEndpoints().getGetSessions());
        HttpHeaders headers = new HttpHeaders();
        headers.add("CustomerKey", "Key: ".concat(customerKey));
        headers.add("SystemKey", "Key: ".concat(outSystemConfiguration.getKey()));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return outSystemRestTemplate.exchange(url, HttpMethod.GET, requestEntity,
                SessionsDto.class, sessionType).getBody();
    }

    @Override
    public SessionDto getSessionById(Long sessionId) {
        String url = outSystemConfiguration.getUrl().concat(outSystemConfiguration.getEndpoints().getGetSession());
        HttpHeaders headers = new HttpHeaders();
        headers.add("SystemKey", "Key: ".concat(outSystemConfiguration.getKey()));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return outSystemRestTemplate.exchange(url, HttpMethod.GET, requestEntity,
                SessionDto.class, sessionId).getBody();
    }

    @Override
    public void betToSession(Long sessionId, String customerKey) {
        String url = outSystemConfiguration.getUrl().concat(outSystemConfiguration.getEndpoints().getMakeBet());
        HttpHeaders headers = new HttpHeaders();
        headers.add("CustomerKey", "Key: ".concat(customerKey));
        headers.add("SystemKey", "Key: ".concat(outSystemConfiguration.getKey()));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        int status = outSystemRestTemplate.exchange(url, HttpMethod.POST, requestEntity,
                String.class, sessionId).getStatusCodeValue();
        ErrorDescription.OUT_SYSTEM_BET_ERROR.throwIfTrue(status != 200);
    }
}
