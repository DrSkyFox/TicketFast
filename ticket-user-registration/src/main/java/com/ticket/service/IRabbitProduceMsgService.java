package com.ticket.service;

import com.example.email.model.MessageMail;

public interface IRabbitProduceMsgService {

    void send(MessageMail mail);

}
