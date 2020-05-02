package com.example.learning.slacklinkage.appointment.domain.model;

import java.time.LocalTime;

public class AppointmentFactory {
    public static Appointment create(String appointeeId, String requesterId,
                                     LocalTime startTime, LocalTime endTime, String place) {
        return new Appointment(appointeeId,requesterId, startTime, endTime, place);
    }

    public static Appointment sample() {
        return new Appointment("appointeeId", "requesterId",
                LocalTime.of(12, 0), LocalTime.of(14,0), "サンプル地域");
    }
}
