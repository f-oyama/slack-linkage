package com.example.learning.slacklinkage.appointment.presentation.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * Slackへのレスポンスデータのモデルクラス
 */
@Data
public class SlackResponseJson {
    @JsonProperty(value = "user_name")
    public String userName; // メンション先
    public String text; // メッセージ

    public SlackResponseJson(String userName, String text) {
        this.userName = userName;
        this.text = text;
    }
}
