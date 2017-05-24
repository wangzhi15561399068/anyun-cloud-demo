package com.anyun.cloud.demo.api.management.http;

import com.anyun.common.lang.StringUtils;
import com.anyun.common.lang.http.ParamaterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 23/05/2017
 */
public class DefaultManagementApiServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultManagementApiServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String method = request.getMethod();
        String queryString = request.getQueryString();
        String remote = request.getRemoteAddr();
        LOGGER.debug("Remote: {}", remote);
        LOGGER.debug("Path info: {}", pathInfo);
        LOGGER.debug("Method: {}", method);
        LOGGER.debug("Query string: {}", queryString);
        Map<String, List<String>> parameters = ParamaterUtil.getUriQueryParameters(queryString);
        if (StringUtils.isNotEmpty(queryString)) {
            LOGGER.debug("========= Query Parameters ==========");
            for (Map.Entry<String, List<String>> parameter : parameters.entrySet()) {
                LOGGER.debug("Name: {}     Value: {}", parameter.getKey(), parameter.getValue());
            }
        }
    }
}
