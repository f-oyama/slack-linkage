package com.example.learning.slacklinkage.appointment.application.service.impl;

import com.example.learning.slacklinkage.appointment.application.service.AppointmentService;
import com.example.learning.slacklinkage.appointment.application.service.ParseService;
import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.service.CheckService;
import com.example.learning.slacklinkage.appointment.infrastructure.repository.AppointmentRepository;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    ParseService parseService;
    CheckService checkService;
    AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(ParseService parseService, CheckService checkService,
                                  AppointmentRepository appointmentRepository) {
        this.parseService = parseService;
        this.checkService = checkService;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public SlackResponseJson save(AppointmentRequest payload) {
        Appointment appointment = parseService.parseToDomainModel(payload);

        if (checkService.checkConflict(appointment)) {
            logger.error("This appointment is conflicted");
            return parseService.parseToSlackResponse(appointment, false);
        }

        appointmentRepository.save(appointment);
        return parseService.parseToSlackResponse(appointment, true);
    }

    @Override
    public List<Appointment> selectAll() {
        return appointmentRepository.selectAll();
    }

    @Override
    public boolean deleteAll() {
        return appointmentRepository.deleteAll();
    }
}
