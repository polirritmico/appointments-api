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
public class InvalidScheduleRangeException extends RuntimeException {
    public InvalidScheduleRangeException(LocalDateTime init, LocalDateTime end) {
        String msg = String.format("Appointment has an invalid time range: %s, %s", init, end);
        log.error(msg);
        super(msg);
    }
}
