package com.example.learning.slacklinkage.appointment.domain.service.impl;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.service.CheckService;
import com.example.learning.slacklinkage.appointment.infrastructure.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CheckServiceImpl implements CheckService {

    AppointmentRepository appointmentRepository;

    public CheckServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public boolean checkConflict(Appointment requestAppointment) {
        List<Appointment> appointmentList = appointmentRepository.selectAll();

        Optional<Appointment> conflictAppointment = appointmentList.stream()
                .filter(appointment1 -> appointment1.getAppointeeId().equals(requestAppointment.getAppointeeId()))
                .filter(appointment1 -> isConflictTime(appointment1, requestAppointment))
                .findFirst();

        return conflictAppointment.isPresent();
    }

    private boolean isConflictTime(Appointment src, Appointment dist) {
        return src.getStartTime().isBefore(dist.getEndTime())
                && src.getEndTime().isAfter(dist.getStartTime());
    }

    // コンフリクト確認メソッド
    private boolean isConflictTime(LocalTime startTimeA, LocalTime endTimeA,
                                   LocalTime startTimeB, LocalTime endTimeB) {
        // コンフリクトしてないと判断する条件
        final boolean isNotConflictCondition = endTimeA.isBefore(startTimeB) || startTimeA.isAfter(endTimeB);

        // コンフリクトしていればtrueを返す
        return !(isNotConflictCondition);
    }
}
