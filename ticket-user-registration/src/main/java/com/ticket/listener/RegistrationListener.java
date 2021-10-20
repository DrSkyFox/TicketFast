package com.ticket.listener;


import com.ticket.email.model.MessageMail;
import com.ticket.entities.account.Account;
import com.ticket.events.OnRegistrationCompleteEvent;
import com.ticket.service.IRabbitProduceMsgService;
import com.ticket.service.ITokenVerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {


    private final ITokenVerifyService service;
    private final IRabbitProduceMsgService msgService;

    @Autowired
    public RegistrationListener(ITokenVerifyService service, IRabbitProduceMsgService msgService) {
        this.service = service;
        this.msgService = msgService;
    }

    @Override
    public void onApplicationEvent(final OnRegistrationCompleteEvent event) {
        log.info("Registration event start: event {}", event.toString());
        this.confirmRegistration(event);
    }

    private void confirmRegistration(final OnRegistrationCompleteEvent event) {

        final Account account = event.getAccount();
        final String token = UUID.randomUUID()
            .toString();

        service.createVerificationTokenForUser(account, token);
        log.info("Send Registration Task in query");
        msgService.send(
                MessageMail.builder()
                        .recipientAddress(account.getEmail())
                        .subject("Registration Account Confirm")
                        .text(message(event.getAppUrl(), token, account))
                        .build()
        );

    }

    private String message(String uRL, String token, Account account) {
        StringBuilder builder = new StringBuilder();
        builder.append(uRL)
                .append("/account/registrationConfirm?")
                .append("token=")
                .append(token);
        return builder.toString();
    }

}
