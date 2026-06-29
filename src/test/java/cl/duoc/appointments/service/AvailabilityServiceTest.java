/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.appointments.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.duoc.appointments.model.TimeSlot;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest
@ActiveProfiles("test")
class AvailabilityServiceTest {

    @Autowired
    private AvailabilityService service;

    @Test
    void shouldGenerateTimeSlotsRangeCorrectly() {
        LocalTime givenStartTime = LocalTime.of(21, 15);
        LocalTime givenEndTime = LocalTime.of(23, 30);
        int givenSlotDuration = 15;
        List<TimeSlot> expected = List.of(
                new TimeSlot(LocalTime.of(21, 15), LocalTime.of(21, 30)),
                new TimeSlot(LocalTime.of(21, 30), LocalTime.of(21, 45)),
                new TimeSlot(LocalTime.of(21, 45), LocalTime.of(22, 00)),
                new TimeSlot(LocalTime.of(22, 00), LocalTime.of(22, 15)),
                new TimeSlot(LocalTime.of(22, 15), LocalTime.of(22, 30)),
                new TimeSlot(LocalTime.of(22, 30), LocalTime.of(22, 45)),
                new TimeSlot(LocalTime.of(22, 45), LocalTime.of(23, 00)),
                new TimeSlot(LocalTime.of(23, 00), LocalTime.of(23, 15)),
                new TimeSlot(LocalTime.of(23, 15), LocalTime.of(23, 30)));

        List<TimeSlot> actual = ReflectionTestUtils.invokeMethod(
                service, "generateTimeSlotsRange", givenStartTime, givenEndTime, givenSlotDuration);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRoundToNextTimeSlotSizeCorrectly() {
        int givenSlotDuration = 15;
        LocalTime givenTime1 = LocalTime.of(12, 1);
        LocalTime expected1 = LocalTime.of(12, 15);
        LocalTime givenTime2 = LocalTime.of(00, 16);
        LocalTime expected2 = LocalTime.of(00, 30);
        LocalTime givenTime3 = LocalTime.of(23, 46);
        LocalTime expected3 = LocalTime.of(00, 00);
        LocalTime givenTime4 = LocalTime.of(8, 00);
        LocalTime expected4 = LocalTime.of(8, 00);

        LocalTime actual;
        actual = ReflectionTestUtils.invokeMethod(service, "roundToSlot", givenTime1, givenSlotDuration);
        assertEquals(expected1, actual);
        actual = ReflectionTestUtils.invokeMethod(service, "roundToSlot", givenTime2, givenSlotDuration);
        assertEquals(expected2, actual);
        actual = ReflectionTestUtils.invokeMethod(service, "roundToSlot", givenTime3, givenSlotDuration);
        assertEquals(expected3, actual);
        actual = ReflectionTestUtils.invokeMethod(service, "roundToSlot", givenTime4, givenSlotDuration);
        assertEquals(expected4, actual);
    }

    @Test
    void shouldRoundToTimeSlotsOfOtherSizesCorrectly() {
        int givenSlotDuration = 7;
        LocalTime given = LocalTime.of(12, 8);
        LocalTime expected = LocalTime.of(12, 14);

        LocalTime output = ReflectionTestUtils.invokeMethod(service, "roundToSlot", given, givenSlotDuration);
        assertEquals(expected, output);
    }
}
