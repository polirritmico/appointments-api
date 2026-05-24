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
public class ClientAppointmentNotFoundException extends DomainException {
    public ClientAppointmentNotFoundException(Long clientId) {
        String msg = "Client with id '" + clientId + "' does not have an appointment registered";
        super(msg, HttpStatus.NOT_FOUND);
    }
}
