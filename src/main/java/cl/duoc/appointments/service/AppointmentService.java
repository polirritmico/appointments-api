/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.exception.AppointmentNotFoundException;
import cl.duoc.appointments.mapper.DtoModelMapper;
import cl.duoc.appointments.model.Appointment;
import cl.duoc.appointments.model.ClinicalRecord;
import cl.duoc.appointments.repository.AppointmentRepository;
import cl.duoc.appointments.repository.ClinicalRecordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {
    private final AppointmentRepository repo;
    private final ClinicalRecordRepository recordRepo;

    private final DtoModelMapper mapper;

    private void logRequest(String msg) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(msg + " by user " + auth.getName());
    }

    public List<AppointmentResponse> findAll() {
        logRequest("Starting findAll");
        return repo.findAllByDeletedAtIsNull().stream()
                .map(mapper::toAppointmentResponse)
                .toList();
    }

    public AppointmentResponse getAppointmentById(Long appointmentId) {
        logRequest("Starting getAppointment with id: " + appointmentId);
        return mapper.toAppointmentResponse(repo.findByIdAndDeletedAtIsNull(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId)));
    }

    public AppointmentWithRecordsResponse getAppointmentWithDetails(Long appointmentId) {
        logRequest("Starting getAppointmentWithDetails with id: " + appointmentId);
        Appointment appt = repo.findByIdAndDeletedAtIsNull(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        List<ClinicalRecord> records = recordRepo.findByAppointmentId(appt.getId());
        return mapper.toAppointmentWithRecordsResponse(appt, records);
    }

    public List<AppointmentResponse> getAppointmentsByPetId(Long petId) {
        logRequest("Starting getAppointmentsByPetId with id: " + petId);
        return repo.findByPetIdAndDeletedAtIsNull(petId).stream()
                .map(mapper::toAppointmentResponse)
                .toList();
    }
}
