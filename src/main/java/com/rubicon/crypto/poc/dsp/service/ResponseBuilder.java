package com.rubicon.crypto.poc.dsp.service;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.google.openrtb.OpenRtb.BidResponse.SeatBid.Bid;
import com.rubicon.crypto.poc.dsp.environment.Environment;
import com.rubicon.crypto.poc.dsp.environment.EnvironmentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ResponseBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseBuilder.class);
    private static final String CONTENT = "https://www.google.com.ua/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
    private final EnvironmentService environmentService;

    public ResponseBuilder(EnvironmentService environmentService) {
        this.environmentService = environmentService;
    }

    public Bid build(String impId) {
        Environment env = environmentService.getEnv();

        Bid bid = Bid.newBuilder()
                     .setId(UUID.randomUUID().toString())
                     .setImpid(impId)
                     .setPrice(ThreadLocalRandom.current().nextInt(1, 100))
                     .setIurl(CONTENT)
                     .setNurl("http://" + env.getHost() + ":" + env.getPort() + "/win/" + impId)
                     .setBurl("http://" + env.getHost() + ":" + env.getPort() + "/billing/" + impId)
                     .build();
        LOGGER.debug("Bid was created:\n{}", bid);

        return bid;
    }
}
