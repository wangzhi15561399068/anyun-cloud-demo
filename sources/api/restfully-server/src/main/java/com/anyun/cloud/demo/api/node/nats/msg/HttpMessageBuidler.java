package com.anyun.cloud.demo.api.node.nats.msg;

import com.anyun.cloud.demo.common.etcd.GsonUtil;
import com.anyun.cloud.demo.common.registry.entity.NodeType;
import com.anyun.common.lang.FileUtil;
import com.anyun.common.lang.msg.GeneralMessage;
import io.nats.client.Message;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 09/06/2017
 */
public class HttpMessageBuidler implements MessageBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpMessageBuidler.class);
    private static final List<String> SUPPORTED_CONTENT_TYPES = new ArrayList<>();
    private static final String REQUEST_ENCODING = "utf-8";
    private static final String HTTP_HEADER_PREFIX = "API.HTTP.HEADER.";
    private static final String SUBJECT = "anyuncloud service invoke";

    static {
        SUPPORTED_CONTENT_TYPES.addAll(Arrays.asList(
                "application/json",
                "application/xml",
                "text/html",
                "text/plain",
                "text/xml"
        ));
    }

    private HttpServletRequest request;
    private String deviceId;
    private String serviceNode;

    public HttpMessageBuidler withHttpRequest(HttpServletRequest request) {
        this.request = request;
        return this;
    }

    public HttpMessageBuidler withDeviceId(String deviceId) {
        this.deviceId = NodeType.API_REST_NODE.name() + "." + deviceId;
        return this;
    }

    public void withServiceNode(String serviceNode) {
        this.serviceNode = serviceNode;
    }


    @Override
    public Message build() {
        GeneralMessage generalMessage = new GeneralMessage();
        generalMessage.setBody(getRequestBody());
        generalMessage.setFrom(deviceId);
        generalMessage.setHeaders(getHeaders());
        generalMessage.setMessageId(generateMessageId());
        generalMessage.setSubject(SUBJECT);
        generalMessage.setTo(serviceNode);
        Message message = new Message();
        String json = GsonUtil.getUtil().toJson(generalMessage);
        LOGGER.debug("Build message: \n {}", json);
        message.setData(json.getBytes());
        message.setReplyTo(deviceId);
        message.setSubject(SUBJECT);
        return message;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(HTTP_HEADER_PREFIX + headerName, request.getHeader(headerName));
        }
        return headers;
    }

    private String generateMessageId() {
        String salt = UUID.randomUUID().toString();
        Hashids hashids = new Hashids(salt, 16);
        return hashids.encode(System.nanoTime());
    }

    private String getRequestBody() {
        if (request.getMethod().equals("GET")
                || request.getMethod().equals("HEAD")
                || request.getMethod().equals("DELETE")) {
            LOGGER.warn("Unsupported request method [{}]", request.getMethod());
            return "";
        }
        if (!isSupportedContentType()) {
            LOGGER.warn("Unsupported request content type [{}]", request.getContentType());
            return "";
        }
        try {
            int contentSize = request.getContentLength();
            LOGGER.debug("Request content size: {} bytes", contentSize);
            if (contentSize == 0)
                return "";
            String body = FileUtil.cat(request.getInputStream(), REQUEST_ENCODING);
            LOGGER.debug("Request body [\n{}\n]", body);
            return body;
        } catch (Exception ex) {
            LOGGER.warn("Request content read error [{}]", ex.getMessage(), ex);
            return "";
        }
    }

    private boolean isSupportedContentType() {
        for (String type : SUPPORTED_CONTENT_TYPES) {
            if (type.equals(request.getContentType()))
                return true;
        }
        return false;
    }
}
