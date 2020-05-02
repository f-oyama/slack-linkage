package com.example.learning.slacklinkage.config;

import com.example.learning.slacklinkage.config.filter.HttpHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Configuration = 設定ファイルの役割を担うクラス
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean registerHTTPHeaderFilter () {
        FilterRegistrationBean bean = new FilterRegistrationBean(new HttpHeaderFilter()); // FilterにHTTPHeaderFilterを登録
        bean.addUrlPatterns("/*"); // Filterを適用するURLパターンを登録
        bean.setOrder(Integer.MIN_VALUE); // FilterChainの順序をセット
        return bean;
    }
}
