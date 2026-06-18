/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.dto.request;

import cl.duoc.appointments.model.Schedule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchAvailabilityRequest {
    @NotNull(message = "La consulta debe tener fecha.")
    @FutureOrPresent(message = "La consulta no puede agendarse en el pasado")
    private LocalDate date;

    @NotNull(message = "La consulta debe tener fecha y hora")
    private LocalTime startTime;

    @NotNull(message = "La consulta debe tener fecha y hora de término")
    private LocalTime endTime;

    @Positive(message = "Los bloques horarios deben ser mayores a cero")
    private int slotDurationMinutes;

    @NotNull(message = "La lista de consultas es obligatoria")
    @Valid
    private List<Schedule> vetSchedules;
}
