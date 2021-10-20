package com.ticket.servimp;


import com.ticket.email.model.MessageMail;
import com.ticket.response.Response;
import com.ticket.config.ConfigurationProducerMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailServiceQueryImp implements com.ticket.service.MailServiceQuery {

    private final AmqpTemplate rabbit;

    private ConfigurationProducerMail configuration;

    @Autowired
    public MailServiceQueryImp(AmqpTemplate rabbit, ConfigurationProducerMail configuration) {
        this.rabbit = rabbit;
        this.configuration = configuration;
    }


    @Override
    public Response send(MessageMail messageModel) {
        log.info("Get MessageMail: {}", messageModel);
        try {
            log.info("Send to query");

            rabbit.convertAndSend(configuration.getNameExchange(),
                    configuration.getNameRoutingKey(),
                    messageModel);

            log.info("Sended...");
        }catch (AmqpException e){
            log.error("Failed send msg: {}", e.getMessage());
            return Response.builder().message(e.getMessage()).code(100).build();
        }
        log.info("Complete send and get response");
        return Response.builder().message("Success").code(0).build();
    }

}
