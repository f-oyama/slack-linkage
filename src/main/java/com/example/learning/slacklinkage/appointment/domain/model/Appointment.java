package com.example.learning.slacklinkage.appointment.domain.model;

import lombok.Data;

import java.time.LocalTime;

/*
 * 予約情報のドメインモデルクラス
 */
@Data
public class Appointment {
    String appointeeId; // アポを取られた人のID
    String requesterId; // 予約した人のID
    LocalTime startTime; // 開始時刻
    LocalTime endTime; // 終了時刻
    String place; // 場所

    private Appointment(){}

    // 同じパッケージのFactoryからのみ参照させるため protected
    protected Appointment(String appointeeId, String requesterId, LocalTime startTime, LocalTime endTime, String place) {
        this.appointeeId = appointeeId;
        this.requesterId = requesterId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.place = place;
    }

    // TODO ドメイン貧血症 = ドメインの業務知識を持ってて欲しいのにないこと
    //  ドメインの業務知識に基づいた処理を持たせる
    //  時間チェックもここにあると良い？
    //  ドメインモデルに持たせるというのがあまりイメージつかめてないので、サンプルコードを漁りたい
    //  業務要件が変更される場合に、domain下を見れば変更する箇所がわかるのが良い
}
