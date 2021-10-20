package com.ticket.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

@Slf4j
@org.springframework.context.annotation.Configuration
public class RabbitConfig {

    private final ConfigurationProducerReg configuration;

    @Autowired
    public RabbitConfig(ConfigurationProducerReg configuration) {
        this.configuration = configuration;
    }

    @Bean
    public Queue queue() {
        return new Queue(configuration.getNameQueue(), false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(configuration.getNameExchange());
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        log.info("Binding Rabbit: {}, {}, {}", queue, exchange, configuration.getNameRoutingKey());
        return BindingBuilder.bind(queue).to(exchange).with(configuration.getNameRoutingKey());
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }


}
