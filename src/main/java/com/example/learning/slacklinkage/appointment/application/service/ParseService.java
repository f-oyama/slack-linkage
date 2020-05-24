package com.example.learning.slacklinkage.appointment.application.service;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;

/*
 * Presentation層のデータをApplication / Domain 層で使う形に変換するサービス
 */
public interface ParseService {
    Appointment parseToDomainModel (AppointmentRequest payload);
    SlackResponseJson parseToSlackResponse(Appointment appointment, boolean isSuccess);
}
