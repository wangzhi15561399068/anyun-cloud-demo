package com.anyun.cloud.demo.monitor.command;

import com.anyun.cloud.demo.monitor.message.entity.NatsRuntimeStatisticsEntity;
import com.anyun.cloud.demo.monitor.message.NatsStatisticsService;
import com.anyun.common.lang.bean.InjectorsBuilder;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliAvailabilityIndicator;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.stereotype.Component;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/6/6
 */
@Component
public class MessageBusMonitorCommands implements CommandMarker {
    private NatsStatisticsService service;

    public MessageBusMonitorCommands() {
        service = InjectorsBuilder.getBuilder().getInstanceByType(NatsStatisticsService.class);
    }

    @CliAvailabilityIndicator({
            "bus-msg statistics"
    })
    public boolean isSimpleAvailable() {
        return true;
    }

    @CliCommand(value = "bus-msg statistics", help = "Print nats statistics.")
    public String statistics() {
        try {
            NatsRuntimeStatisticsEntity entity = service.getStatistics();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return "";
    }
}
