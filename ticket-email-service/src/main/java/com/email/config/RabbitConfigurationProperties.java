package com.email.config;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter(AccessLevel.MODULE)
@ToString
@Configuration
public class RabbitConfigurationProperties {


    private String nameQueue;

    private String nameExchange;

    private String nameRoutingKey;

    @Value("${service.rabbit.queue}")
    public void setNameQueue(String nameQueue) {
        log.info("serviceNameQueue: {}", nameQueue);
        if((nameQueue == "") || (nameQueue == null)) {
            nameQueue = "service.queue";
        }
        this.nameQueue = nameQueue;
    }

    @Value("${service.rabbit.exchange}")
    public void setNameExchange(String nameExchange) {
        log.info("exchangeNameExchange: {}", nameExchange);
        if((nameExchange == "") || (nameExchange == null)) {
            nameExchange = "service.exchange";
        }
        this.nameExchange = nameExchange;
    }

    @Value("${service.rabbit.routingKey}")
    public void setNameRoutingKey(String nameRoutingKey) {
        log.info("nameRoutingKey: {}", nameRoutingKey);
        if((nameRoutingKey == "") || (nameRoutingKey == null)) {
            nameRoutingKey = "key";
        }
        this.nameRoutingKey = nameRoutingKey;
    }





}
