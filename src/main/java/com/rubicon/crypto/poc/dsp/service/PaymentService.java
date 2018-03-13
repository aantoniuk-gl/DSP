package com.rubicon.crypto.poc.dsp.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.rubicon.crypto.poc.dsp.monitoring.MonitoringClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final NotificationStorage notificationStorage;
    private final MonitoringClient monitoringClient;

    public PaymentService(NotificationStorage notificationStorage, MonitoringClient monitoringClient) {
        this.notificationStorage = notificationStorage;
        this.monitoringClient = monitoringClient;
    }

    @Scheduled(fixedDelay = 5000)
    public void schedulePayment() {
        Set<String> winNotifications = notificationStorage.getAllWinNotifications();

        List<String> billedImpIds = notificationStorage.getAllBillingNotifications()
                                                       .stream()
                                                       .filter(winNotifications::contains)
                                                       .collect(Collectors.toList());
        billedImpIds.forEach(this::payment);
    }

    private void payment(String impId) {
        LOGGER.debug("Impression with id '{}' was paid", impId);
        monitoringClient.log("Impression was paid with impId=" + impId);
        notificationStorage.removeWinNotification(impId);
        notificationStorage.removeBillingNotification(impId);
    }
}
