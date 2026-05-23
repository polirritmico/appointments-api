/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import cl.duoc.appointments.dto.request.ClinicalRecordCreationRequest;
import cl.duoc.appointments.dto.response.ClinicalRecordResponse;
import cl.duoc.appointments.exception.AppointmentNotFoundException;
import cl.duoc.appointments.exception.ClinicalRecordNotFoundException;
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
public class ClinicalRecordService {
    private final ClinicalRecordRepository repo;
    private final AppointmentRepository appointmentRepo;

    private final DtoModelMapper mapper;

    private void logRequest(String msg) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info(msg + " by user " + auth.getName());
    }

    public List<ClinicalRecordResponse> findAll() {
        logRequest("Starting findAll");
        return repo.findAll().stream().map(mapper::toClinicalRecordResponse).toList();
    }

    public ClinicalRecordResponse findById(Long recordId) {
        logRequest("Starting findById with id: " + recordId);
        return repo.findById(recordId)
                .map(mapper::toClinicalRecordResponse)
                .orElseThrow(() -> new ClinicalRecordNotFoundException(recordId));
    }

    public List<ClinicalRecordResponse> findByPetId(Long petId) {
        logRequest("Starting findByPetId with id: " + petId);
        return repo.findByPetId(petId).stream()
                .map(mapper::toClinicalRecordResponse)
                .toList();
    }

    public ClinicalRecordResponse saveRecord(ClinicalRecordCreationRequest req) {
        logRequest("Starting saveRecord with appointmentId: " + req.getAppoinmentId());
        Appointment appt = appointmentRepo
                .findById(req.getAppoinmentId())
                .orElseThrow(() -> new AppointmentNotFoundException(req.getAppoinmentId()));
        ClinicalRecord newRecord = repo.save(mapper.clinicalRecordFromCreationRequest(req, appt));
        return mapper.toClinicalRecordResponse(newRecord);
    }
}
