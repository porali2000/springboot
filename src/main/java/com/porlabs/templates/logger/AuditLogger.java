package com.porlabs.templates.logger;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public final class AuditLogger {

    private static final EventLogger<AuditLogger> LOGGER =
            new EventLogger<>(AuditLogger.class);
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    @Value("${app.isAuditLogEnabled}")
    private boolean isAuditLogEnabled;

//    @Autowired
//    public AuditEventRepo auditRepo;

    private AuditLogger() {
    }

    public void persist(ApiEvent event) {
        try {
            if (isAuditLogEnabled) {
                Assert.notNull(event, "AuditEvent object cannot be null");
                final AuditEvent auditEvent = AuditEvent.build(event);
//                this.auditRepo.save(MODEL_MAPPER.map(auditEvent, AuditEventDto.class));
                LOGGER.audit(auditEvent);
            }
        } catch (Exception e) {
            LOGGER.error(ErrorEvent.build(e).eventStatus(EventStatus.FAIL));
        }
    }
}
