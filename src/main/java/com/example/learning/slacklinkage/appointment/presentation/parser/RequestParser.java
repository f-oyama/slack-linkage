package com.example.learning.slacklinkage.appointment.presentation.parser;

import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RequestParser {
    private static final String TOKEN_NAME_KEY = "token";
    private static final String USER_NAME_KEY = "user_name";
    private static final String TEXT_KEY = "text";

    private static final int APPOINTEE_INDEX = 0;
    private static final int START_TIME_INDEX = 1;
    private static final int END_TIME_INDEX = 2;
    private static final int PLACE_INDEX = 3;

    // 要素数の最大数
    private static final int MAX_ELEMENTS = PLACE_INDEX + 1;

    public static AppointmentRequest parsePayload(String request) {
         // decode 処理
        String decodedRequest;
        try {
            decodedRequest = decode(request);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to decode");
        }

        // parse 処理
        String[] params = decodedRequest.split("&");

        // TODO スマートにMapへ変換したい
        Map<String, String> map = new HashMap<>();
        Arrays.stream(params).forEach(param -> {
            String[] keyValue = param.split("=");
            map.put(Objects.requireNonNull(keyValue[0], ""),
                    Objects.requireNonNull(keyValue[1], ""));
        });

        // 必須要素のチェック
        Optional<String> tokenOpt = Optional.of(map.get(TOKEN_NAME_KEY));
        Optional<String> userNameOpt = Optional.of(map.get(USER_NAME_KEY));
        Optional<String> textOpt = Optional.of(map.get(TEXT_KEY));
        if (tokenOpt.isEmpty() || userNameOpt.isEmpty() || textOpt.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // TODO textの項目が右記の順番通りでなければいけない問題 : text=予約対象の人(0),18:00(1),20:00(2),place(3)
        String[] appointInfo = textOpt.get().split(",");
        if (appointInfo.length < MAX_ELEMENTS) {
            // TODO 要素が足りない時は例外
            throw new IllegalArgumentException();
        }

        // TODO ParserがFactory扱いになっているけどFactoryにした方が良いのだろうか・・・
        //  でも、parseしてるしな、という悩み
        return new AppointmentRequest(tokenOpt.get(), userNameOpt.get(), textOpt.get(),
                appointInfo[APPOINTEE_INDEX], parseToTime(appointInfo[START_TIME_INDEX]),
                parseToTime(appointInfo[END_TIME_INDEX]), appointInfo[PLACE_INDEX]);
    }

    private static String decode(String requestBody) throws UnsupportedEncodingException {
        String decodedResult = URLDecoder.decode(requestBody, StandardCharsets.UTF_8);
        System.out.println("デコード : from " + requestBody + " to " + decodedResult);
        return decodedResult;
    }

    private static LocalTime parseToTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
    }
}
