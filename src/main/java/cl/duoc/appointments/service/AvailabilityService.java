/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import cl.duoc.appointments.dto.request.SearchAvailabilityRequest;
import cl.duoc.appointments.dto.response.SearchAvailabilityResponse;
import cl.duoc.appointments.mapper.DtoModelMapper;
import cl.duoc.appointments.model.Appointment;
import cl.duoc.appointments.model.ClinicSchedule;
import cl.duoc.appointments.model.Schedule;
import cl.duoc.appointments.model.Schedule.ProfessionalId;
import cl.duoc.appointments.model.TimeSlot;
import cl.duoc.appointments.repository.AppointmentRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AvailabilityService {
    private final DtoModelMapper mapper;
    private final AppointmentRepository repo;

    public List<SearchAvailabilityResponse> getAvailableScheduleHoursUseCase(SearchAvailabilityRequest req) {
        int slot = req.getSlotDurationMinutes();
        LocalTime from = roundToSlot(req.getStartTime(), slot);
        LocalTime until = roundToSlot(req.getEndTime(), slot);
        LocalDate date = req.getDate();
        List<Schedule> availability = req.getVetSchedules();

        ClinicSchedule schedule = buildAvailabilitySchedule(availability, from, until, slot);
        LocalDateTime dateTimeFrom = LocalDateTime.of(date, from);
        List<Appointment> appts = schedule.proIds().stream()
                .flatMap(id -> repo.findProfessionalDaySchedule(id.value(), dateTimeFrom).stream())
                .toList();

        return mapper.toSearchAvailabilityResponse(buildAvailabilityList(appts, schedule), availability);
    }

    private LocalTime roundToSlot(LocalTime time, int slotDurationMinutes) {
        int minutes = time.getMinute();
        int remainder = minutes % slotDurationMinutes;
        if (remainder == 0) {
            return time.withSecond(0).withNano(0);
        }
        return time.plusMinutes(slotDurationMinutes - remainder).withSecond(0).withNano(0);
    }

    private ClinicSchedule buildAvailabilitySchedule(
            List<Schedule> professionalSchedules, LocalTime startTime, LocalTime endTime, int slotDuration) {
        ClinicSchedule schedule = new ClinicSchedule();

        for (Schedule professionalSchedule : professionalSchedules) {
            LocalTime from = professionalSchedule.getFromTime().isAfter(startTime)
                    ? professionalSchedule.getFromTime()
                    : startTime;
            LocalTime until = professionalSchedule.getUntilTime().isBefore(endTime)
                    ? professionalSchedule.getUntilTime()
                    : endTime;
            if (from.isAfter(until)) {
                continue;
            }
            schedule.set(professionalSchedule.getId(), generateTimeSlotsRange(from, until, slotDuration));
        }
        return schedule;
    }

    private List<TimeSlot> generateTimeSlotsRange(LocalTime startTime, LocalTime endTime, int slotMinutes) {
        List<TimeSlot> slots = new ArrayList<>();
        for (LocalTime from = startTime, until = from.plusMinutes(slotMinutes);
                !until.isAfter(endTime);
                from = until, until = until.plusMinutes(slotMinutes)) {
            slots.add(new TimeSlot(from, until));
        }
        return slots;
    }

    private ClinicSchedule buildAvailabilityList(List<Appointment> currentAppts, ClinicSchedule schedule) {
        for (Appointment appt : currentAppts) {
            ProfessionalId busyVet = new ProfessionalId(appt.getProfessionalId());
            schedule.removeTimeSlotRange(
                    busyVet,
                    appt.getScheduleAt().toLocalTime(),
                    appt.getEndScheduleAt().toLocalTime());
        }
        return schedule;
    }
}
