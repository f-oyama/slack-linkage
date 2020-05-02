package com.example.learning.slacklinkage.appointment.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc // MockMvc のDI設定
@SpringBootTest
        // Spring bootの設定など、@Configurationはここで登録されそう、なかったらフィルター反映されなかったし
class AppointmentControllerTest {

    @Autowired // Beanが登録されたプールからインスタンス取得
            MockMvc mockMvc;

    @Test
    void debug() throws Exception {
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/debug");
        MvcResult result = mockMvc.perform(getRequest)
                .andExpect(status().isOk())
                .andExpect(header().exists("Access-Control-Allow-Origin"))
                .andExpect(header().exists("Content-Length"))
                .andReturn();
        assertEquals("*", result.getResponse().getHeaderValue("Access-Control-Allow-Origin")); // keyに対する値を取得
    }
}