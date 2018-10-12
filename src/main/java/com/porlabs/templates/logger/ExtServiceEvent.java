package com.porlabs.templates.logger;


import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString(callSuper = true)
public class ExtServiceEvent extends Event {
    private String operation;
    private String user;
    transient private Map<String, Object> params = new HashMap<>();

    private ExtServiceEvent() {
    }

    public static ExtServiceEvent build(String operation) {
        ExtServiceEvent endpointLogger = new ExtServiceEvent();
        endpointLogger.operation = operation;
        return endpointLogger;
    }

    public ExtServiceEvent params(String param, Object value) {
        this.params.put(param, value);
        return this;
    }

    @Override
    public ExtServiceEvent eventStatus(EventStatus eventStatus) {
        super.eventStatus = eventStatus;
        return this;
    }

    public ExtServiceEvent user(String user) {
        this.user = user;
        return this;
    }
}
