package com.example.learning.slacklinkage.appointment.presentation.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 * 予約Jsonモデルクラス
 */
@Data
public class AppointmentJson {
    private String appointeeId;
    private String requesterId;

    @JsonFormat(pattern = "yyyy/MM/dd") // @JsonFormatを使うと、文字列のパターンを制限できる
    private LocalDate appointDate; // アポイントの日程

    @JsonFormat(pattern = "HH:mm") // @JsonFormatを使うと、時刻のパターンも制限できる
    private LocalTime startTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

    public AppointmentJson(String appointeeId, String requesterId, LocalDate appointDate,
                           LocalTime startTime, LocalTime endTime) {
        this.appointeeId = appointeeId;
        this.requesterId = requesterId;
        this.appointDate = appointDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public AppointmentJson(String appointeeId, String requesterId) {
        this.appointeeId = appointeeId;
        this.requesterId = requesterId;
        this.appointDate = LocalDate.now();
        this.startTime = LocalTime.now();
        this.endTime = LocalTime.now().minusHours(1); // 日付が入っていなければ1時間後をセット
    }
}
