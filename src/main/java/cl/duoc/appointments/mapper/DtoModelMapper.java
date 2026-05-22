/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.mapper;

import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.model.Appointment;
import cl.duoc.appointments.model.ClinicalRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {
    public AppointmentResponse toAppointmentResponse(Appointment appointment) {
        return AppointmentResponse.builder()
                .id(appointment.getId())
                .clientId(appointment.getClientId())
                .petId(appointment.getPetId())
                .scheduleAt(appointment.getScheduleAt())
                .status(appointment.getStatus().name())
                .createdAt(appointment.getCreatedAt())
                .updatedAt(appointment.getUpdatedAt())
                .deletedAt(appointment.getDeletedAt())
                .build();
    }

    public ClinicalRecordResponse toClinicalRecordResponse(ClinicalRecord record) {
        return ClinicalRecordResponse.builder()
                .id(record.getId())
                .appointmentId(record.getAppointment().getId())
                .clientId(record.getClientId())
                .petId(record.getPetId())
                .professionalId(record.getProfessionalId())
                .diagnosis(record.getDiagnosis())
                .treatment(record.getTreatment())
                .notes(record.getNotes())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getCreatedAt())
                .build();
    }
}
