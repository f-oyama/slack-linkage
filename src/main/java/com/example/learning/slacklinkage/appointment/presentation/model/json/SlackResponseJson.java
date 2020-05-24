package com.example.learning.slacklinkage.appointment.presentation.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/*
 * Slackへのレスポンスデータのモデルクラス
 */
@Data
public class SlackResponseJson {
    public String username; // メンション先
    public String text; // メッセージ

    public SlackResponseJson(String username, String text) {
        this.username = username;
        this.text = text;
    }
}
