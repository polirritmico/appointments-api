/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.mapper;

import cl.duoc.appointments.dto.request.AppointmentCreationRequest;
import cl.duoc.appointments.dto.request.ClinicalRecordCreationRequest;
import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.model.Appointment;
import cl.duoc.appointments.model.AppointmentStatus;
import cl.duoc.appointments.model.ClinicalRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DtoModelMapper {
    public AppointmentResponse toAppointmentResponse(Appointment appt) {
        return AppointmentResponse.builder()
                .id(appt.getId())
                .clientId(appt.getClientId())
                .petId(appt.getPetId())
                .professionalId(appt.getProfessionalId())
                .scheduleAt(appt.getScheduleAt())
                .endScheduleAt(appt.getEndScheduleAt())
                .status(appt.getStatus().name())
                .createdAt(appt.getCreatedAt())
                .updatedAt(appt.getUpdatedAt())
                .build();
    }

    public ClinicalRecordResponse toClinicalRecordResponse(ClinicalRecord record) {
        return ClinicalRecordResponse.builder()
                .id(record.getId())
                .appointmentId(record.getAppointment().getId())
                .clientId(record.getClientId())
                .petId(record.getPetId())
                .professionalId(record.getProfessionalId())
                .visitReason(record.getVisitReason())
                .diagnosis(record.getDiagnosis())
                .treatment(record.getTreatment())
                .notes(record.getNotes())
                .createdAt(record.getCreatedAt())
                .updatedAt(record.getCreatedAt())
                .build();
    }

    public AppointmentWithRecordsResponse toAppointmentWithRecordsResponse(
            Appointment appt, List<ClinicalRecord> records) {
        return AppointmentWithRecordsResponse.builder()
                .clientId(appt.getClientId())
                .petId(appt.getClientId())
                .professionalId(appt.getProfessionalId())
                .scheduleAt(appt.getScheduleAt())
                .endScheduleAt(appt.getEndScheduleAt())
                .status(appt.getStatus().name())
                .records(records.stream().map(this::toClinicalRecordResponse).toList())
                .createdAt(appt.getCreatedAt())
                .updatedAt(appt.getUpdatedAt())
                .build();
    }

    public ClinicalRecord clinicalRecordFromCreationRequest(ClinicalRecordCreationRequest req, Appointment appt) {
        return ClinicalRecord.builder()
                .appointment(appt)
                .clientId(appt.getClientId())
                .petId(appt.getPetId())
                .professionalId(appt.getProfessionalId())
                .visitReason(req.getVisitReason())
                .diagnosis(req.getDiagnosis())
                .treatment(req.getTreatment())
                .notes(req.getNotes())
                .build();
    }

    public Appointment appointmentFromCreationRequest(AppointmentCreationRequest req) {
        return Appointment.builder()
                .clientId(req.getClientId())
                .petId(req.getClientId())
                .professionalId(req.getProfessionalId())
                .scheduleAt(req.getScheduleAt())
                .endScheduleAt(req.getEndScheduleAt())
                .status(AppointmentStatus.PENDING)
                .build();
    }
}
