package com.ticket.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Getter
@Setter
@ToString
@Component
public class ConfigurationProducerReg {

    @Value("${service.rabbit.queue}")
    private String nameQueue;

    @Value("${service.rabbit.exchange}")
    private String nameExchange;

    @Value("${service.rabbit.routingKey}")
    private String nameRoutingKey;
}
