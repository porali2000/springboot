package com.porlabs.templates.logger;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuditEvent extends Event {
    private String actor;
    private String event;
    private String apiPath;
    private String status;
    private String time;

    public AuditEvent() {
        status = EventStatus.FAIL.name();
    }

    public static AuditEvent build(ApiEvent event) {
        AuditEvent local = new AuditEvent();
//        local.actor = event.getUser();
        local.event = event.getClass().getSimpleName();
//        local.apiPath = event.getPath();
//        local.time = event.getStartTime().toString();
//        local.status = (event.getEventStatus() == EventStatus.COMPLETE)
//                ? event.getEventStatus().name() : local.status;
        return local;
    }

    @Override
    Event eventStatus(EventStatus eventStatus) {
        return null;
    }
}
