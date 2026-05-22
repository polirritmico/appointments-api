/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Represents the current state of the appointment in its lifecycle.\n\n"
                + "* PENDING: The appointment was requested but not yet approved.\n"
                + "* CONFIRMED: The time slot is locked in the calendar.\n"
                + "* COMPLETED: The appointment finished successfully.\n"
                + "* CANCELED: The appointment was aborted beforehand.\n"
                + "* MISSED: The client failed to appear without prior cancellation.")
public enum AppointmentStatus {
    PENDING,
    CONFIRMED,
    ARRIVED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED,
    MISSED
}
