/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import cl.duoc.appointments.dto.request.AppointmentCreationRequest;
import cl.duoc.appointments.dto.request.SearchAvailabilityRequest;
import cl.duoc.appointments.dto.response.AppointmentResponse;
import cl.duoc.appointments.dto.response.AppointmentWithRecordsResponse;
import cl.duoc.appointments.exception.AppointmentNotFoundException;
import cl.duoc.appointments.exception.AppointmentScheduleConflictException;
import cl.duoc.appointments.exception.InvalidScheduleRangeException;
import cl.duoc.appointments.mapper.DtoModelMapper;
import cl.duoc.appointments.model.Appointment;
import cl.duoc.appointments.model.AppointmentStatus;
import cl.duoc.appointments.model.ClinicalRecord;
import cl.duoc.appointments.model.Schedule.ProfessionalId;
import cl.duoc.appointments.repository.AppointmentRepository;
import cl.duoc.appointments.repository.ClinicalRecordRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
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

    public List<AppointmentResponse> getAppointmentsByProfessionalId(Long professionalId) {
        logRequest("Starting getAppointmentsByProfessionalId with id: " + professionalId);
        return repo.findByProfessionalIdAndDeletedAtIsNull(professionalId).stream()
                .map(mapper::toAppointmentResponse)
                .toList();
    }

    public List<AppointmentResponse> getProfessionalDaySchedules(SearchAvailabilityRequest req) {
        LocalDateTime targetDate = req.getDate().atStartOfDay();
        List<ProfessionalId> professionalIds =
                req.getVetSchedules().stream().map(schedule -> schedule.getId()).toList();
        return professionalIds.stream()
                .flatMap(id -> repo.findProfessionalDaySchedule(id.value(), targetDate).stream())
                .map(mapper::toAppointmentResponse)
                .toList();
    }

    @Transactional
    public AppointmentResponse updateStatus(Long appointmentId, AppointmentStatus status) {
        logRequest("Starting updateStatus with appointment id: " + appointmentId);
        Appointment appointment =
                repo.findById(appointmentId).orElseThrow(() -> new AppointmentNotFoundException(appointmentId));
        appointment.setStatus(status);
        return mapper.toAppointmentResponse(appointment);
    }

    @Transactional
    public AppointmentResponse scheduleAppointment(AppointmentCreationRequest req) {
        Appointment appt = mapper.appointmentFromCreationRequest(req);

        validateScheduleTimeConsistency(appt);
        validateProfessionalAvailabilityAtSchedule(appt);
        validateClientAvailabilityAtSchedule(appt);
        validatePetAvailabilityAtSchedule(appt);

        return mapper.toAppointmentResponse(repo.save(appt));
    }

    private void validateScheduleTimeConsistency(Appointment appt) {
        LocalDateTime reqStart = appt.getScheduleAt();
        LocalDateTime reqEnd = appt.getEndScheduleAt();

        if (reqStart.equals(reqEnd) || reqStart.isAfter(reqEnd)) {
            throw new InvalidScheduleRangeException(reqStart, reqEnd);
        }
    }

    private void validateProfessionalAvailabilityAtSchedule(Appointment appt) {
        List<Appointment> existingAppointments =
                repo.findProfessionalDaySchedule(appt.getProfessionalId(), appt.getScheduleAt());

        if (hasAnyOverlap(appt, existingAppointments)) {
            String professional = "professional with id: " + appt.getProfessionalId();
            throw new AppointmentScheduleConflictException(professional, appt.getScheduleAt());
        }
    }

    private void validateClientAvailabilityAtSchedule(Appointment appt) {
        List<Appointment> existingAppointments = repo.findClientDaySchedule(appt.getClientId(), appt.getScheduleAt());

        if (hasAnyOverlap(appt, existingAppointments)) {
            String client = "client with id: " + appt.getClientId();
            throw new AppointmentScheduleConflictException(client, appt.getScheduleAt());
        }
    }

    private void validatePetAvailabilityAtSchedule(Appointment appt) {
        List<Appointment> existingAppointments = repo.findPetDaySchedule(appt.getPetId(), appt.getScheduleAt());

        if (hasAnyOverlap(appt, existingAppointments)) {
            String pet = "pet with id: " + appt.getPetId();
            throw new AppointmentScheduleConflictException(pet, appt.getScheduleAt());
        }
    }

    private boolean hasAnyOverlap(Appointment requestedAppt, List<Appointment> scheduledAppts) {
        // TODO: Check behaviour if 2 parallel executions run at the same time
        LocalDateTime requestedStart = requestedAppt.getScheduleAt();
        LocalDateTime requestedEnd = requestedAppt.getEndScheduleAt();

        for (Appointment scheduled : scheduledAppts) {
            LocalDateTime scheduledStart = scheduled.getScheduleAt();
            LocalDateTime scheduledEnd = scheduled.getEndScheduleAt();

            if (requestedStart.isBefore(scheduledEnd) && requestedEnd.isAfter(scheduledStart)) {
                return true;
            }
        }

        return false;
    }
}
