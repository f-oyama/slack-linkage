package com.example.learning.slacklinkage.appointment.presentation.controller;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.presentation.model.AppointmentRequest;
import com.example.learning.slacklinkage.appointment.presentation.model.json.SlackResponseJson;
import com.example.learning.slacklinkage.appointment.presentation.parser.RequestParser;
import com.example.learning.slacklinkage.appointment.application.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentAPIController {

    private static final Logger logger = LoggerFactory.getLogger(AppointmentAPIController.class);

    private AppointmentService appointmentService;

    public AppointmentAPIController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/")
    public String index() {
        return "Running";
    }

    /*
     * 予約API
     */
    @PostMapping("/appoint")
    public SlackResponseJson appoint(@RequestBody String body) {// @RequestBodyをつけると、RequestBodyをStringとして受けられる
        logger.info("appoint received data = " + body);

        AppointmentRequest request;
        try {
            request = RequestParser.parsePayload(body);
        } catch (IllegalArgumentException e) {
            // TODO SlackResponseJson 作成をこの層でやる、SlackResponseJsonは外部とのやりとりなので、この層だけ知っていれば良い
            return new SlackResponseJson("@here", e.getMessage());
        }

        SlackResponseJson response = appointmentService.save(request);
        return response;
    }

    /*
     * Json形式で受け付ける予約API
     */
    @PostMapping("/appoint/json")
    public String appointJson(@RequestBody Appointment appointment) {// @RequestBody + データ型を引数にセットすると、Jsonを型にマッピングしてくれる
        logger.info("appointJson received with " + appointment);
        return appointment.toString();
    }

    // Debug用データ削除API
    @GetMapping("/appoint/delete/all")
    public String deleteAll() {
        boolean successDelete = appointmentService.deleteAll();
        if (successDelete) {
            return "Data clear is completed";
        } else {
            return "Data clear is failed";
        }
    }
}