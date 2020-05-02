package com.example.learning.slacklinkage.appointment.infrastructure.repository;


import com.example.learning.slacklinkage.appointment.domain.model.Appointment;

import java.util.List;

public interface AppointmentRepository {
    public Appointment save(Appointment appointment);
    public List<Appointment> selectAll();
    public boolean deleteAll();
}
