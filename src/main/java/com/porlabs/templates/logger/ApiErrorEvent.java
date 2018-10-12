package com.porlabs.templates.logger;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class ApiErrorEvent extends Event {
    private String errorMessage;
    private Throwable exceptionType;

    public static ApiErrorEvent build(Throwable th) {
        ApiErrorEvent ev = new ApiErrorEvent();
        ev.errorMessage = th.getMessage();
        ev.exceptionType = th;
        return ev;
    }

    public static ApiErrorEvent build(Throwable th, Event event) {
        ApiErrorEvent ev = new ApiErrorEvent();
        ev.errorMessage = th.getMessage();
        ev.exceptionType = th;
        ev.startTime = event.startTime;
        return ev;
    }

    public ApiErrorEvent exception(Throwable th) {
        this.exceptionType = th;
        this.errorMessage = th.getMessage();
        return this;
    }

    @Override
    public ApiErrorEvent eventStatus(EventStatus eventStatus) {
        super.eventStatus = eventStatus;
        return this;
    }
}
