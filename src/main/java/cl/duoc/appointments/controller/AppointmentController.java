/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.controller;

import cl.duoc.appointments.dto.request.AppointmentCreationRequest;
import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.model.AppointmentStatus;
import cl.duoc.appointments.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "Provides appointments CRUD operations.")
public class AppointmentController {
    private final AppointmentService service;

    @Operation(
            summary = "List all appointments",
            description = "Retrieves a full list of all recorded appointments in the system.")
    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(
            summary = "Get appointment by Id",
            description = "Retrieves basic details of a specific appointment using its unique identifier.")
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(service.getAppointmentById(appointmentId));
    }

    @Operation(
            summary = "Get full appointment details",
            description = "Retrieves a specific appointment combined with its associated clinical records.")
    @GetMapping("/{appointmentId}/full")
    public ResponseEntity<AppointmentWithRecordsResponse> getAppointmentWithRecords(@PathVariable Long appointmentId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Operation(
            summary = "Get appointments by pet Id",
            description = "Retrieves the historical and upcoming appointments for a specific pet.")
    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<AppointmentResponse>> getScheduledAppointments(@PathVariable Long petId) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Operation(
            summary = "Update appointment status",
            description =
                    "Updates the current lifecycle status of the appointment (e.g., CONFIRMED, COMPLETED, CANCELED).")
    @PatchMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponse> updateStatus(
            @PathVariable Long appointmentId, @RequestParam AppointmentStatus status) {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Operation(summary = "Schedule a new appointment", description = "Creates a new appointment record in the system.")
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentCreationRequest req) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
