package com.example.learning.slacklinkage.appointment.infrastructure.repository;


import com.example.learning.slacklinkage.appointment.domain.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    Appointment save(Appointment appointment);
    List<Appointment> selectAll();
    boolean deleteAll();
}
