package com.rubicon.crypto.poc.dsp.web;

import com.rubicon.crypto.poc.dsp.monitoring.MonitoringClient;
import com.rubicon.crypto.poc.dsp.service.NotificationStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    private NotificationStorage notificationStorage;
    @Autowired
    private MonitoringClient monitoringClient;

    @GetMapping("/win/{impId}")
    public void win(@PathVariable String impId) {
        notificationStorage.addWinNotification(impId);

        monitoringClient.log("Win Notification was received with impId=" + impId);
    }

    @GetMapping("/billing/{impId}")
    public void billing(@PathVariable String impId) {
        notificationStorage.addBillingNotification(impId);

        monitoringClient.log("Billing Notification was received with impId=" + impId);
    }
}
