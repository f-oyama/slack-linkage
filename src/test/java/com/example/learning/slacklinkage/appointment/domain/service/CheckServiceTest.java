package com.example.learning.slacklinkage.appointment.domain.service;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.model.AppointmentFactory;
import com.example.learning.slacklinkage.appointment.infrastructure.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckServiceTest {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    CheckService checkService;

    @Test
    void testCheckConflictWithNotConflict() {
        // TODO 本来ならMockで書きたい
        Appointment appointed = AppointmentFactory.create("appointee","requester",
                LocalTime.of(12,0), LocalTime.of(14,0),"test place");
        appointmentRepository.save(appointed);

        Appointment appointed2 = AppointmentFactory.create("appointee","requester",
                LocalTime.of(18,0), LocalTime.of(20,0),"test place");
        appointmentRepository.save(appointed2);

        Appointment request = AppointmentFactory.create("appointee","requester",
                LocalTime.of(15,0), LocalTime.of(17,0),"test place");
        assertFalse(checkService.checkConflict(request));
    }

    @Test
    void testCheckConflictWithConflict_StartTime() {
        // TODO 本来ならMockで書きたい
        Appointment appointed = AppointmentFactory.create("appointee","requester",
                LocalTime.of(12,0), LocalTime.of(14,0),"test place");
        appointmentRepository.save(appointed);

        Appointment request = AppointmentFactory.create("appointee","requester",
                LocalTime.of(13,0), LocalTime.of(17,0),"test place");

        assertTrue(checkService.checkConflict(request));
    }

    @Test
    void testCheckConflictWithConflict_EndTime() {
        // TODO 本来ならMockで書きたい
        Appointment appointed = AppointmentFactory.create("appointee","requester",
                LocalTime.of(12,0), LocalTime.of(14,0),"test place");
        appointmentRepository.save(appointed);

        Appointment request = AppointmentFactory.create("appointee","requester",
                LocalTime.of(10,0), LocalTime.of(13,0),"test place");

        assertTrue(checkService.checkConflict(request));
    }
}