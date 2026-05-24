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
public class InvalidScheduleRangeException extends DomainException {
    public InvalidScheduleRangeException(LocalDateTime init, LocalDateTime end) {
        String msg = String.format("Appointment has an invalid time range: %s to %s", init, end);
        super(msg, HttpStatus.BAD_REQUEST);
    }
}
