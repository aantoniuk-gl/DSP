package com.rubicon.crypto.poc.dsp.web;

import com.google.openrtb.OpenRtb.BidResponse.SeatBid.Bid;
import com.rubicon.crypto.poc.dsp.monitoring.MonitoringClient;
import com.rubicon.crypto.poc.dsp.service.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BidController {

    @Autowired
    private ResponseBuilder responseBuilder;
    @Autowired
    private MonitoringClient monitoringClient;

    @GetMapping("/bid/{impId}")
    public Bid bid(@PathVariable String impId) {
        Bid bidResponse = responseBuilder.build(impId);

        monitoringClient.log("Bid was created:\n" + bidResponse);

        return bidResponse;
    }
}
