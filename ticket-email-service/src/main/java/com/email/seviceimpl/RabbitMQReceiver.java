package com.email.seviceimpl;



import com.email.service.MailSendingService;
import com.ticket.email.model.MessageMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class RabbitMQReceiver {

    private final MailSendingService service;

    @Autowired
    public RabbitMQReceiver(MailSendingService service) {
        this.service = service;
    }


    @RabbitListener(queues = "#{rabbitConfigurationProperties.getNameQueue()}")
    public void onMessage(MessageMail messageModel, Message message) {

        log.info("Get Message from Queue {}, key {}", messageModel, message.getMessageProperties().getReceivedRoutingKey() );
        service.send(messageModel);
        log.info("send email");
    }


//    @RabbitListener(queues = "#{rabbitConfigurationProperties.getNameQueue()}")
//    public void onMessage(MessageMail messageModel, Message message) {
//        log.info("Get Message from Queue {}, key {}", messageModel, message.getMessageProperties().getReceivedRoutingKey() );
//        service.send(messageModel);
//        log.info("send email");
//    }




}
