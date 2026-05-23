/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AppointmentNotFoundException extends RuntimeException {
    public AppointmentNotFoundException(Long appointmentId) {
        String msg = "Appointment with id '" + appointmentId + "' not found";
        log.error(msg);
        super(msg);
    }
}
