/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.exception;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class AppointmentScheduleConflictException extends DomainException {
    public AppointmentScheduleConflictException(String conflictedSubject, LocalDateTime scheduleAt) {
        String msg = String.format("The %s has a schedule conflict at %s", conflictedSubject, scheduleAt);
        super(msg, HttpStatus.CONFLICT);
    }
}
