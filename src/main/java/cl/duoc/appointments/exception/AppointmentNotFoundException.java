/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class AppointmentNotFoundException extends DomainException {
    public AppointmentNotFoundException(Long appointmentId) {
        String msg = "Appointment with id '" + appointmentId + "' not found";
        super(msg, HttpStatus.NOT_FOUND);
    }
}
