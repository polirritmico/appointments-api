/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.exception;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppointmentScheduleConflictException extends RuntimeException {
    public AppointmentScheduleConflictException(String conflictedSubject, LocalDateTime scheduleAt) {
        String msg = String.format("The %s has a schedule conflict at %s", conflictedSubject, scheduleAt);
        log.error(msg);
        super(msg);
    }
}
