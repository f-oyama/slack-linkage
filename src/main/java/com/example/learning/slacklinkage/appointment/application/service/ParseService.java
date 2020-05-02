package com.example.learning.slacklinkage.appointment.application.service;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;

/*
 * Presentation層のデータをApplication / Domain 層で使う形に変換するサービス
 */
public interface ParseService {
    public Appointment parseToDomainModel (AppointmentRequest payload);

}
