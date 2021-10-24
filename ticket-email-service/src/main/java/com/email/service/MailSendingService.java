package com.email.service;


import com.ticket.email.model.MessageMail;


public interface MailSendingService {

    void send(MessageMail messageModel);


}
