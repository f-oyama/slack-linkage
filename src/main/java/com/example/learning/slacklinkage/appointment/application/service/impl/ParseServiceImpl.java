package com.example.learning.slacklinkage.appointment.application.service.impl;

import com.example.learning.slacklinkage.appointment.application.service.ParseService;
import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.model.AppointmentFactory;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;
import org.springframework.stereotype.Service;

/*
 * Presentation層のモデルとApplication層のモデルを変換するサービス
 */
@Service
public class ParseServiceImpl implements ParseService {

    @Override
    public Appointment parseToDomainModel(AppointmentRequest payload) {
        return AppointmentFactory.create(payload.getAppointeeId(), payload.getUserName(),
                payload.getStartTime(), payload.getEndTime(), payload.getPlace());
    }

    @Override
    public SlackResponseJson parseToSlackResponse(Appointment appointment, boolean isSuccess) {
        // TODO 成功失敗のみでなく、何故成功失敗したかをResponseに含める
        if (isSuccess) {
            return new SlackResponseJson("here", appointment.toString() + " の予約が受理されました");
        } else {
            return new SlackResponseJson("here", appointment.toString() + " の予約は取れませんでした");
        }
    }
}
