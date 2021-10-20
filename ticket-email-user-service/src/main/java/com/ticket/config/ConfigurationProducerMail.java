package com.ticket.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@Setter
@ToString
@Configuration
public class ConfigurationProducerMail {

    @Value("${service.rabbit.queue}:service.queue")
    private String nameQueue;

    @Value("${service.rabbit.exchange}:service.exchange")
    private String nameExchange;

    @Value("${service.rabbit.routingKey}:key")
    private String nameRoutingKey;





}
