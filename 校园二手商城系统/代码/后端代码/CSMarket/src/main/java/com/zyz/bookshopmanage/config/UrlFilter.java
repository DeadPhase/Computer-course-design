package com.zyz.bookshopmanage.config;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author Lenovo
 * @version 1.0
 * @data 2022/10/12 23:04
 */
@Configuration
public class UrlFilter implements Filter {

    public final static String DEFAULT_URI_ENCODE = "UTF-8";

    private FilterConfig config = null;
    private String encode = null;

    @Override
    public void destroy() {
        config = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        String ch = URLDecoder.decode(uri, encode);
        if(uri.equals(ch)) {
            chain.doFilter(req, res);
            return;
        }
        ch = ch.substring(request.getContextPath().length());
        config.getServletContext().getRequestDispatcher(ch).forward(req, res);

    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        this.encode = config.getInitParameter("DEFAULT_URI_ENCODE");
        if(this.encode == null) {
            this.encode = DEFAULT_URI_ENCODE;
        }
    }

}