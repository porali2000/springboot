package com.porlabs.templates.logger;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WarnEvent extends Event {
    private String warnMessage;

    public static WarnEvent build(String msg) {
        WarnEvent ev = new WarnEvent();
        ev.warnMessage = msg;
        return ev;
    }

    @Override
    public WarnEvent eventStatus(EventStatus eventStatus) {
        super.eventStatus = eventStatus;
        return this;
    }
}
