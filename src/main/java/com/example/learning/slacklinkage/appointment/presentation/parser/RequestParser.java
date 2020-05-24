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

    public static AppointmentRequest parsePayload(String requestParam) throws IllegalArgumentException {
         // decode 処理
        String decodedRequestParam;
        try {
            decodedRequestParam = decode(requestParam);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to decode");
        }

        // parse 処理
        Map<String, String> paramMap = parseParamToMap(decodedRequestParam);

        // 必須要素が満たされているかチェック
       checkParamIsValid(paramMap);

        // 本文を分割して、予約情報へ変換する
        String[] appointInfo = paramMap.get(TEXT_KEY).split(",");

        // TODO ParserがFactory扱いになっているけどFactoryにした方が良いのだろうか・・・
        //  でも、parseしてるしな、という悩み
        return new AppointmentRequest(paramMap.get(TOKEN_NAME_KEY), paramMap.get(USER_NAME_KEY), paramMap.get(TEXT_KEY),
                appointInfo[APPOINTEE_INDEX], parseToTime(appointInfo[START_TIME_INDEX]),
                parseToTime(appointInfo[END_TIME_INDEX]), appointInfo[PLACE_INDEX]);
    }

    private static Map<String, String> parseParamToMap (String requestParam) {
        String[] params = requestParam.split("&");

        // TODO スマートにMapへ変換したい
        Map<String, String> paramMap = new HashMap<>();
        Arrays.stream(params).forEach(param -> {
            String[] keyValue = param.split("=");
            paramMap.put(Objects.requireNonNull(keyValue[0], ""),
                    Objects.requireNonNull(keyValue[1], ""));
        });

        return paramMap;
    }

    /*
     * 必要なパラメータが揃っているかを確認する
     */
    private static boolean checkParamIsValid(Map<String,String> paramMap) throws IllegalArgumentException {
        final String formatError = "フォーマットが不正です [予約:予約対象(0),開始時刻(1),終了時刻(2),場所(3)] のフォーマットで記入してください";
        final String parseTimeError = "開始時刻(1), 終了時刻(2) どちらかの時刻フォーマットが不正です。 HH:mm 形式で入力してください。";
        final String equalTimeError = "開始時刻(1), 終了時刻(2) が同じ場合は予約できません。";

        Optional<String> tokenOpt = Optional.of(paramMap.get(TOKEN_NAME_KEY));
        Optional<String> userNameOpt = Optional.of(paramMap.get(USER_NAME_KEY));
        Optional<String> textOpt = Optional.of(paramMap.get(TEXT_KEY));
        if (tokenOpt.isEmpty() || userNameOpt.isEmpty() || textOpt.isEmpty()) {
            throw new IllegalArgumentException(formatError);
        }

        // TODO textの項目が右記の順番通りでなければいけない問題 : text=予約対象の人(0),18:00(1),20:00(2),place(3)
        String[] appointInfo = textOpt.get().split(",");

        // 値のチェック
        if (appointInfo.length < MAX_ELEMENTS) {
            // 要素が足りないケース
            throw new IllegalArgumentException(formatError);
        }

        // 時刻のチェック
        LocalTime startTime, endTime;
        // 不正な値かをチェック
        try {
            startTime = parseToTime(appointInfo[START_TIME_INDEX]);
            endTime = parseToTime(appointInfo[END_TIME_INDEX]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(parseTimeError);
        }
        // 時刻に幅があるかをチェック
        if (startTime.equals(endTime)) {
            // 幅がなければエラー
            throw new IllegalArgumentException(equalTimeError);
        }

        return true;
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
