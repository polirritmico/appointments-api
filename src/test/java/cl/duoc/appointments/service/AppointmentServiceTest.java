/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cl.duoc.appointments.exception.InvalidScheduleRangeException;
import cl.duoc.appointments.model.Appointment;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@ActiveProfiles("test")
class AppointmentServiceTest {

    @Autowired
    private AppointmentService service;

    private static final LocalDate FOODAY = LocalDate.of(2026, 1, 1);

    private Appointment appt(LocalTime start, LocalTime end) {
        return Appointment.builder()
                .scheduleAt(LocalDateTime.of(FOODAY, start))
                .endScheduleAt(LocalDateTime.of(FOODAY, end))
                .build();
    }

    @Test
    void shouldReturnTrueWithOverlappingAppts() {
        LocalDateTime now = LocalDateTime.of(FOODAY, LocalTime.of(16, 15));
        Appointment givenNewAppt = Appointment.builder()
                .scheduleAt(now)
                .endScheduleAt(now.plusMinutes(45))
                .build();
        List<Appointment> givenScheduledAppts = List.of(
                appt(LocalTime.of(15, 30), LocalTime.of(16, 00)),
                appt(LocalTime.of(16, 00), LocalTime.of(17, 30)),
                appt(LocalTime.of(17, 30), LocalTime.of(18, 00)));
        boolean expected = true;

        boolean actual = ReflectionTestUtils.invokeMethod(service, "hasAnyOverlap", givenNewAppt, givenScheduledAppts);
        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnFalseWithNoOverlappingAppts() {
        LocalDateTime now = LocalDateTime.of(FOODAY, LocalTime.of(16, 30));
        Appointment givenNewAppt = Appointment.builder()
                .scheduleAt(now)
                .endScheduleAt(now.plusMinutes(15))
                .build();
        List<Appointment> givenScheduledAppts = List.of(
                appt(LocalTime.of(15, 30), LocalTime.of(16, 00)),
                appt(LocalTime.of(16, 15), LocalTime.of(16, 30)),
                appt(LocalTime.of(16, 45), LocalTime.of(17, 00)));
        boolean expected = false;

        boolean actual = ReflectionTestUtils.invokeMethod(service, "hasAnyOverlap", givenNewAppt, givenScheduledAppts);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRaiseInvalidScheduleRangeExceptionWithInvalidTimeSchedule() {
        Appointment given = Appointment.builder()
                .scheduleAt(LocalDateTime.now().plusHours(2))
                .endScheduleAt(LocalDateTime.now().plusHours(1))
                .build();
        Class<InvalidScheduleRangeException> expected = InvalidScheduleRangeException.class;

        assertThrows(
                expected, () -> ReflectionTestUtils.invokeMethod(service, "validateScheduleTimeConsistency", given));
    }

    @Test
    void shouldRaiseInvalidScheduleRangeExceptionWithZeroLengthTimeSchedule() {
        LocalDateTime now = LocalDateTime.now();
        Appointment given =
                Appointment.builder().scheduleAt(now).endScheduleAt(now).build();
        Class<InvalidScheduleRangeException> expected = InvalidScheduleRangeException.class;

        assertThrows(
                expected, () -> ReflectionTestUtils.invokeMethod(service, "validateScheduleTimeConsistency", given));
    }

    @Test
    void shouldNotRaiseInvalidScheduleRangeExceptionWithValidTimeSchedule() {
        Appointment given = Appointment.builder()
                .scheduleAt(LocalDateTime.now().plusHours(2))
                .endScheduleAt(LocalDateTime.now().plusHours(3))
                .build();

        ReflectionTestUtils.invokeMethod(service, "validateScheduleTimeConsistency", given);
    }
}
