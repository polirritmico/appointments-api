/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
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
    private ProfessionalId id;

    @JsonProperty("vetNombre")
    private String name;

    @JsonProperty("horaInicio")
    private LocalTime fromTime;

    @JsonProperty("horaFin")
    private LocalTime untilTime;
}
