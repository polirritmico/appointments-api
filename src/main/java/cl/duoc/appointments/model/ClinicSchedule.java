/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.model;

import cl.duoc.appointments.model.Schedule.ProfessionalId;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicSchedule {
    private final Map<ProfessionalId, List<TimeSlot>> schedules = new HashMap<>();

    public List<TimeSlot> get(ProfessionalId proId) {
        return schedules.getOrDefault(proId, List.of());
    }

    public Map<ProfessionalId, List<TimeSlot>> asMap() {
        return Map.copyOf(schedules);
    }

    public List<ProfessionalId> proIds() {
        return schedules.keySet().stream().toList();
    }

    public void add(ProfessionalId proId, TimeSlot slot) {
        schedules.computeIfAbsent(proId, _ -> new ArrayList<>()).add(slot);
    }

    public void set(ProfessionalId proId, List<TimeSlot> slots) {
        schedules.put(proId, slots);
    }

    public void removeTimeSlotRange(ProfessionalId proId, LocalTime from, LocalTime until) {
        List<TimeSlot> slots = schedules.get(proId);
        if (slots == null) {
            return;
        }
        slots.removeIf(
                slot -> slot.getStartTime().isBefore(until) && slot.getEndTime().isAfter(from));
    }
}
