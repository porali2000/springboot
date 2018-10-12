package com.porlabs.templates.logger;


import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString(callSuper = true)
public class ApiEvent extends Event {
    private String path;
    private String user;
    transient private Map<String, Object> params = new HashMap<>();

    private ApiEvent() {
    }

    public static ApiEvent build(String path) {
        ApiEvent endpointLogger = new ApiEvent();
        endpointLogger.path = path;
        return endpointLogger;
    }

    public ApiEvent params(String param, Object value) {
        this.params.put(param, value);
        return this;
    }

    @Override
    public ApiEvent eventStatus(EventStatus eventStatus) {
        super.eventStatus = eventStatus;
        return this;
    }

    public ApiEvent user(String user) {
        this.user = user;
        return this;
    }
}
