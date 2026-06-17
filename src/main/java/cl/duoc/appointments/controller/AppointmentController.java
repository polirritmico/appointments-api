/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.controller;

import cl.duoc.appointments.api.AppointmentApi;
import cl.duoc.appointments.dto.request.AppointmentCreationRequest;
import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.model.AppointmentStatus;
import cl.duoc.appointments.service.AppointmentService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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
public class AppointmentController implements AppointmentApi {
    private final AppointmentService service;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponse> getAppointment(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(service.getAppointmentById(appointmentId));
    }

    @GetMapping("/{appointmentId}/full")
    public ResponseEntity<AppointmentWithRecordsResponse> getAppointmentWithRecords(@PathVariable Long appointmentId) {
        return ResponseEntity.ok(service.getAppointmentWithDetails(appointmentId));
    }

    @GetMapping("/pet/{petId}")
    public ResponseEntity<List<AppointmentResponse>> getScheduledAppointments(@PathVariable Long petId) {
        return ResponseEntity.ok(service.getAppointmentsByPetId(petId));
    }

    @PatchMapping("/{appointmentId}/status")
    public ResponseEntity<AppointmentResponse> updateStatus(
            @PathVariable Long appointmentId, @RequestParam AppointmentStatus status) {
        return ResponseEntity.ok(service.updateStatus(appointmentId, status));
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(@Valid @RequestBody AppointmentCreationRequest req) {
        return ResponseEntity.ok(service.scheduleAppointment(req));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<AppointmentResponse>> getProfessionalSchedules(
            @RequestParam List<Long> professionalIds,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(service.getProfessionalSchedules(professionalIds, date));
    }
}
