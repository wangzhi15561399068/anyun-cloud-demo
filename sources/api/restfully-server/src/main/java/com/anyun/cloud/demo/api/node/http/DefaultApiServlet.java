package com.anyun.cloud.demo.api.node.http;

import com.anyun.common.lang.StringUtils;
import com.anyun.common.lang.http.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/5/19
 */
public class DefaultApiServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultApiServlet.class);

    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException,
            IOException {
        String servletPath = request.getServletPath();
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String method = request.getMethod();
        String queryString = request.getQueryString();

        LOGGER.debug("Servlet path: {}", servletPath);
        LOGGER.debug("Context Path: {}", contextPath);
        LOGGER.debug("Path info: {}", pathInfo);
        LOGGER.debug("Method: {}", method);
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null && headerNames.hasMoreElements()) {
            LOGGER.debug("=========Request Headers==========");
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                LOGGER.debug("Name: {}     Value: {}", name,request.getHeader(name));
            }
        }
        LOGGER.debug("Query string: {}", queryString);
        if (StringUtils.isNotEmpty(queryString)) {
            LOGGER.debug("=========Query Paramaters==========");
            Map<String, List<String>> paramaters = RequestUtil.getUriQueryParameters(queryString);
            for (Map.Entry<String, List<String>> paramater : paramaters.entrySet()) {
                LOGGER.debug("Name: {}     Value: {}", paramater.getKey(), paramater.getValue());
            }
        }
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello from HelloServlet</h1>");
    }
}

