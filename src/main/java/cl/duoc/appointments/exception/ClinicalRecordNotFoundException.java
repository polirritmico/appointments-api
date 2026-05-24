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
public class ClinicalRecordNotFoundException extends DomainException {
    public ClinicalRecordNotFoundException(Long recordId) {
        String msg = "Clinical record with id '" + recordId + "' not found.";
        super(msg, HttpStatus.NOT_FOUND);
    }
}
