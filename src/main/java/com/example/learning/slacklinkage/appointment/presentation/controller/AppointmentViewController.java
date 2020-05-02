package com.example.learning.slacklinkage.appointment.presentation.controller;

import com.example.learning.slacklinkage.appointment.domain.model.Appointment;
import com.example.learning.slacklinkage.appointment.domain.model.AppointmentFactory;
import com.example.learning.slacklinkage.appointment.application.service.AppointmentService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
public class AppointmentViewController {

    Configuration config;
    AppointmentService appointmentService;

    // Constructor Injection : DIコンテナから該当するインスタンスを取得して注入
    public AppointmentViewController(Configuration config, AppointmentService appointmentService) {
        this.config = config;
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appoint/view/all")
    public String viewAll(Model model) throws IOException, TemplateException {
        List<Appointment> appointList = appointmentService.selectAll();
        model.addAttribute("appointList", appointList);

        String templateName = "/appoint/appoint_list"; // resources/templates下から該当のテンプレートにデータを流し込んで返す
        return templateName;
    }

    @GetMapping("/appoint/view/{userName}")
    public String viewUserAppoint(@PathVariable String userName, Model model) throws IOException, TemplateException {
        // TODO UserNameと一致するappointだけを表示する
        //  今はサンプルデータを表示するだけ
        List<Appointment> appointList = List.of(AppointmentFactory.sample(), AppointmentFactory.sample());
        model.addAttribute("appointList", appointList);

        String templateName = "/appoint/appoint_list";
        return templateName;
    }
}
