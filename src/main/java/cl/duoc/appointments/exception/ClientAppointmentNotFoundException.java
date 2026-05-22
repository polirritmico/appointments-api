/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientAppointmentNotFoundException extends RuntimeException {
    public ClientAppointmentNotFoundException(Long clientId) {
        String msg = "Client with id '" + clientId + "' does not have an appointment registered in the DB.";
        log.error(msg);
        super(msg);
    }
}
