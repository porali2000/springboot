package com.porlabs.templates.logger;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorEvent extends Event {
    private String errorMessage;
    private Throwable exceptionType;
    protected ErrorEvent() {
        super();
    }
    protected ErrorEvent(Throwable th) {
        super();
        this.errorMessage = th.getMessage();
        this.exceptionType = th;
        this.eventStatus(EventStatus.ERROR);
    }

    public static ErrorEvent build(Throwable th) {
        ErrorEvent ev = new ErrorEvent();
        ev.errorMessage = th.getMessage();
        ev.exceptionType = th;
        ev.eventStatus(EventStatus.ERROR);
        return ev;
    }

    public ErrorEvent exception(Throwable th) {
        this.exceptionType = th;
        this.errorMessage = th.getMessage();
        return this;
    }

    @Override
    public ErrorEvent eventStatus(EventStatus eventStatus) {
        super.eventStatus = eventStatus;
        return this;
    }
}
