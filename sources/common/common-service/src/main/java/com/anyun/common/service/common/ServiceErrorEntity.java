package com.anyun.common.service.common;

import com.anyun.common.lang.RandomUtils;
import com.anyun.common.lang.bean.InjectorsBuilder;
import com.anyun.common.lang.msg.GeneralMessage;
import com.anyun.common.service.annotation.CloudService;
import com.anyun.common.service.context.DefaultRouter;
import com.anyun.common.service.context.DefaultSessionContext;
import com.anyun.common.service.context.ServiceContext;
import com.anyun.common.service.context.SessionContext;
import com.anyun.common.service.exchange.DefaultBondBuilder;
import com.anyun.common.service.exchange.DefaultExchange;
import com.anyun.common.service.exchange.Exchange;
import com.anyun.common.service.exchange.ExchangeBond;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 12/06/2017
 */
public class ServiceErrorEntity{
    private String result = "error";
    private int code = 505;
    private String message;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ServiceErrorEntity{" +
                "result='" + result + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
