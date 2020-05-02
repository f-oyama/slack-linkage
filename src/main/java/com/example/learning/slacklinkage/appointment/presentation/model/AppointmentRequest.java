package com.example.learning.slacklinkage.appointment.presentation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalTime;

/*
 * リクエストで受け取ったデータをアプリケーション層で使う形に変換したデータクラス
 */
@Data
public class AppointmentRequest {
    private String token; // 予約時に送られたトークン
    private String userName; // 予約をした人のID
    private String text; // 予約時に送られたテキスト

    // text に含まれるパラメータ
    private String appointeeId; // アポを取られた人のID
    private LocalTime startTime; // 開始時刻
    private LocalTime endTime; // 終了時刻
    private String place; // 場所

    private AppointmentRequest() {
    }

    public AppointmentRequest(String token, String userName, String text,
                              String appointeeId, LocalTime startTime, LocalTime endTime, String place) {
        this.token = token;
        this.userName = userName;
        this.text = text;
        this.appointeeId = appointeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
    }

    public String toString() {
        return "[" + this.getClass().getName() + "]"
                + " : " + "token = " + token
                + " : " + "user_name = " + userName
                + " : " + "text = " + text
                ;
    }
}
