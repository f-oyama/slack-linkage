package com.example.learning.slacklinkage.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// HTTP Header を制御するフィルター
public class HttpHeaderFilter implements Filter {
    private final static Logger logger = LoggerFactory.getLogger(HttpHeaderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初期化処理
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Headerに一律Access-Control-Allow-Origin: * を設定する
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        logger.debug("request = " + request.toString() + " : " + "response = " + response.toString());

        // chain(FilterChain) = フィルター処理の順序情報などを持つ変数
        // chain.doFilter 次のフィルターを実行。このメソッドを呼び出さないと1つのフィルター処理しか実行されない
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 初期化処理
    }
}
