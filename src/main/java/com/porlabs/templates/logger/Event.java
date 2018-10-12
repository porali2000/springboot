package com.porlabs.templates.logger;

import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

@Getter
@ToString
abstract class Event implements LogEvent {
    public static final DateTimeZone TIME_ZONE = DateTimeZone.UTC;
    protected EventStatus eventStatus;
    protected DateTime startTime;
    protected DateTime endtime;
    protected String elapsedTime;

    protected Event() {
        this.startTime = DateTime.now(TIME_ZONE);
    }

    abstract Event eventStatus(EventStatus eventStatus);

    public Event compute() {
        this.endtime = DateTime.now(TIME_ZONE);
        this.elapsedTime = this.endtime.getMillis() - this.startTime.getMillis() + "ms";
        return this;
    }
}
