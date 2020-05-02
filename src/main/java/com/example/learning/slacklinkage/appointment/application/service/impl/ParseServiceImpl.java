package com.example.learning.slacklinkage.appointment.application.service.impl;

import com.example.learning.slacklinkage.appointment.application.service.ParseService;
import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.model.AppointmentFactory;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import org.springframework.stereotype.Service;

@Service
public class ParseServiceImpl implements ParseService {

    @Override
    public Appointment parseToDomainModel(AppointmentRequest payload) {
        return AppointmentFactory.create(payload.getAppointeeId(), payload.getUserName(),
                payload.getStartTime(), payload.getEndTime(), payload.getPlace());
    }
}
