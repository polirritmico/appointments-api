/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.mapper;

import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.model.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {
    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder().id(appointment.getId()).build();
    }
}
