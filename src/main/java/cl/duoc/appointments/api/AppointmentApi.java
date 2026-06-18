/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.api;

import cl.duoc.appointments.dto.request.AppointmentCreationRequest;
import cl.duoc.appointments.dto.request.SearchAvailabilityRequest;
import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.dto.response.SearchAvailabilityResponse;
import cl.duoc.appointments.model.AppointmentStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.ResponseEntity;

@Tag(name = "Appointments", description = "Provides appointments CRUD operations.")
public interface AppointmentApi {
    @Operation(
            summary = "List all appointments",
            description = "Retrieves a full list of all recorded appointments in the system.")
    ResponseEntity<List<AppointmentResponse>> findAll();

    @Operation(
            summary = "Get appointment by Id",
            description = "Retrieves basic details of a specific appointment using its unique identifier.")
    ResponseEntity<AppointmentResponse> getAppointment(Long appointmentId);

    @Operation(
            summary = "Get full appointment details",
            description = "Retrieves a specific appointment combined with its associated clinical records.")
    ResponseEntity<AppointmentWithRecordsResponse> getAppointmentWithRecords(Long appointmentId);

    @Operation(
            summary = "Get appointments by pet Id",
            description = "Retrieves the historical and upcoming appointments for a specific pet.")
    ResponseEntity<List<AppointmentResponse>> getScheduledAppointments(Long petId);

    @Operation(
            summary = "Update appointment status",
            description =
                    "Updates the current lifecycle status of the appointment (e.g., CONFIRMED, COMPLETED, CANCELED).")
    ResponseEntity<AppointmentResponse> updateStatus(Long appointmentId, AppointmentStatus status);

    @Operation(summary = "Schedule a new appointment", description = "Creates a new appointment record in the system.")
    ResponseEntity<AppointmentResponse> createAppointment(AppointmentCreationRequest req);

    @Operation(
            summary = "Get schedules for multiple professionals",
            description = "Retrieves a consolidated list of appointments for various professionals on a specific date.")
    ResponseEntity<List<SearchAvailabilityResponse>> getProfessionalSchedules(SearchAvailabilityRequest req);
}
