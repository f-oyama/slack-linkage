package com.example.learning.slacklinkage.appointment.application.service;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;

import java.util.List;

public interface AppointmentService {
    SlackResponseJson save(AppointmentRequest payload);
    List<Appointment> selectAll();
    boolean deleteAll();
}
