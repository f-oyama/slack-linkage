package com.example.learning.slacklinkage.appointment.infrastructure.repository.impl;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.infrastructure.repository.AppointmentRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/*
 * 予約情報をListとして保存するRepository
 */
@Repository
public class AppointmentListRepositoryImpl implements AppointmentRepository {

    private static List<Appointment> appointmentList = new ArrayList<>();

    @Override
    public Appointment save(Appointment appointment) {
        appointmentList.add(appointment);
        appointmentList.sort(Comparator.comparing(Appointment::getStartTime)); // Comparator.comparingで引数の値照準へでソートできる
        return appointment;
    }

    @Override
    public List<Appointment> selectAll() {
        return List.copyOf(appointmentList);
    }

    @Override
    public boolean deleteAll() {
        try {
            appointmentList.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
