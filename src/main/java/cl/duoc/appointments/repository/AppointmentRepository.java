/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.repository;

import cl.duoc.appointments.model.Appointment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Optional<Appointment> findByIdAndDeletedAtIsNull(Long id);

    List<Appointment> findByClientId(Long clientId);

    List<Appointment> findAllByDeletedAtIsNull();

    List<Appointment> findByPetIdAndDeletedAtIsNull(Long petId);
}
