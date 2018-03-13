package com.rubicon.crypto.poc.dsp.monitoring;

import com.rubicon.crypto.poc.dsp.environment.Environment;
import com.rubicon.crypto.poc.dsp.environment.EnvironmentService;
import com.rubicon.crypto.poc.dsp.service.NotificationStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MonitoringClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationStorage.class);
    private static final String SYSTEM = "DSP(%s:%s)";

    private final RestTemplate restTemplate;
    private final String monitoringEndpoint;
    private final EnvironmentService environmentService;

    public MonitoringClient(@Value("${monitoring.url}") String monitoringEndpoint, EnvironmentService environmentService) {
        this.restTemplate = new RestTemplate();
        this.monitoringEndpoint = monitoringEndpoint;
        this.environmentService = environmentService;
    }

    public void log(String message) {
        Environment env = environmentService.getEnv();
        String system = String.format(SYSTEM, env.getHost(), env.getPort());

        LogEvent logEvent = new LogEvent(system, message);

        try {
            restTemplate.postForEntity(monitoringEndpoint, logEvent, LogEvent.class);
        } catch (Exception e) {
            LOGGER.error("Couldn't sent the message to Monitoring system");
        }
    }
}
