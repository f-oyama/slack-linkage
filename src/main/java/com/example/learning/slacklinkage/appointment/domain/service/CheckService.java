package com.example.learning.slacklinkage.appointment.domain.service;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;

/*
 * 予約情報の確認サービス
 * 予約が可能か可能かどうかをチェックする
 */
public interface CheckService {
    public boolean checkConflict(Appointment appointment);
}
