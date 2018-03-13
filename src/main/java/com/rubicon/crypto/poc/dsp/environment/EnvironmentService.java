package com.rubicon.crypto.poc.dsp.environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentService {
    private final Environment springEnv;

    private com.rubicon.crypto.poc.dsp.environment.Environment env;

    public EnvironmentService(Environment springEnv) {
        this.springEnv = springEnv;
    }

    public com.rubicon.crypto.poc.dsp.environment.Environment getEnv() {
        if (env == null) {
            env = new com.rubicon.crypto.poc.dsp.environment.Environment(getHostAddress(),
                    springEnv.getProperty("local.server.port"));
        }
        return env;
    }

    private String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return  "localhost";
        }
    }
}
