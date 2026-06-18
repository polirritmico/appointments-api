/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    public record ProfessionalId(@JsonValue Long value) {
        public ProfessionalId {
            java.util.Objects.requireNonNull(value);
        }
    }

    @JsonProperty("vetId")
    @NotNull(message = "El id del profesional es obligatorio")
    private ProfessionalId id;

    @JsonProperty("vetNombre")
    @NotBlank(message = "El nombre del profesional es obligatorio")
    private String name;

    @JsonProperty("horaInicio")
    @NotNull(message = "La consulta debe tener hora de inicio")
    private LocalTime fromTime;

    @JsonProperty("horaFin")
    @NotNull(message = "La consulta debe tener hora de fin")
    private LocalTime untilTime;
}
