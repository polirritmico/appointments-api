/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.repository;

import cl.duoc.appointments.model.Appointment;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByIdAndDeletedAtIsNull(Long id);

    List<Appointment> findByClientId(Long clientId);

    List<Appointment> findByProfessionalId(Long professionalId);

    List<Appointment> findAllByDeletedAtIsNull();

    List<Appointment> findByPetIdAndDeletedAtIsNull(Long petId);

    List<Appointment> findByProfessionalIdAndDeletedAtIsNull(Long professionalId);

    List<Appointment> findByProfessionalIdAndScheduleAtBetweenAndDeletedAtIsNull(
            Long professionalId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Appointment> findByClientIdAndScheduleAtBetweenAndDeletedAtIsNull(
            Long clientId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<Appointment> findByPetIdAndScheduleAtBetweenAndDeletedAtIsNull(
            Long petId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    default List<Appointment> findProfessionalDaySchedule(Long professionalId, LocalDateTime schedule) {
        return findByProfessionalIdAndScheduleAtBetweenAndDeletedAtIsNull(
                professionalId,
                schedule.toLocalDate().atStartOfDay(),
                schedule.toLocalDate().atTime(LocalTime.MAX));
    }

    default List<Appointment> findClientDaySchedule(Long clientId, LocalDateTime schedule) {
        return findByClientIdAndScheduleAtBetweenAndDeletedAtIsNull(
                clientId,
                schedule.toLocalDate().atStartOfDay(),
                schedule.toLocalDate().atTime(LocalTime.MAX));
    }

    default List<Appointment> findPetDaySchedule(Long petId, LocalDateTime schedule) {
        return findByPetIdAndScheduleAtBetweenAndDeletedAtIsNull(
                petId,
                schedule.toLocalDate().atStartOfDay(),
                schedule.toLocalDate().atTime(LocalTime.MAX));
    }
}
