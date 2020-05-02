package com.example.learning.slacklinkage.appointment.presentation.controller;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.StringJoiner;

@RestController
public class SampleController {

    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @GetMapping("/sample")
    public String debug () {
        return "debug";
    }

    // SLACK URLがセットされてない場合のmock。slackへ送るデータのチェックなどに使いたい
    @PostMapping("/sample/slack")
    public SlackResponseJson sampleSlack (@RequestBody SlackResponseJson json) {
        logger.debug("LOG [slackMock] = " + json.toString());
        return json;
    }

    @GetMapping("/sample/param")
    // @RequestParam = URLの?以降のクエリパラメータをパースしてくれる
    public String postParam (@RequestParam("id") String id, // keyが一致する値をマッピング
                             @RequestParam Map<String, String> paramMap, // Map形式
                             AppointmentRequest requestParam // クラス形式、@RequestParamがなくてもパラメータのキーとクラスのフィールド名でマッピング
    ) {
        StringJoiner joiner = new StringJoiner("&");
        paramMap.forEach((key, value) -> joiner.add(key + "=" + value));

        joiner.add("requestParam = " + requestParam.toString());

        return joiner.toString();
    }

    @PostMapping("/sample/json")
    // @RequestBody + データ型を引数にセットすると、Jsonを型にマッピングしてくれる
    public Appointment postJson (@RequestBody Appointment appointment) {
            logger.info("appointJson received with " + appointment);
            return appointment;
    }

    /*
     * WebFluxサンプル リアクティブプログラミングの機能
     * TODO データを更新した際に、viewにも即時反映させたいので、これを使って実現可能か調査したい
     *  WebSocket / RSocketも選択肢？
     */
    @GetMapping("/flux")
    Mono<String> hello() {
        // リアクティブストリーム仕様に従ったPublisherの提供
        // Mono = Mono型(単数処理)とFlux型(複数処理)がある
        return Mono.just("Hello World");
    }
}