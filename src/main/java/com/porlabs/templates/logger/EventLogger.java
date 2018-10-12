package com.porlabs.templates.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EventLogger<T> {

    public Logger logger;

    public EventLogger(Class<T> clas) {
        this.logger = LogManager.getLogger(clas);
    }

    public void info(String msg) {
        logger.info(msg);
    }

    public void info(LogEvent obj) {
        this.logger.info(obj.toString());
    }

    public void payload(Object payload) {
        logger.info(payload.toString());
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(LogEvent obj) {
        this.logger.error(obj.toString());
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void warn(LogEvent obj) {
        this.logger.warn(obj.toString());
    }

    public void fatal(String msg) {
        logger.fatal(msg);
    }

    public void fatal(LogEvent obj) {
        this.logger.fatal(obj.toString());
    }

    public void audit(AuditEvent event) {
        logger.info(event.toString());
    }
}
