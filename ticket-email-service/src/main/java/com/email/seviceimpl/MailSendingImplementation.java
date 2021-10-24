package com.email.seviceimpl;


import com.email.config.EmailSMTPConfigurationProperties;
import com.email.service.MailSendingService;
import com.ticket.email.model.MessageMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSendingImplementation implements MailSendingService {


    private JavaMailSender mailSender;
    private EmailSMTPConfigurationProperties properties;

    @Autowired
    public MailSendingImplementation(JavaMailSender mailSender, EmailSMTPConfigurationProperties properties) {
        this.mailSender = mailSender;
        this.properties = properties;
    }

    @Override
    public void send(MessageMail messageModel)
    {
        log.info("MessageModel: {}", messageModel.toString());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(properties.getFromAddress());

        log.info("RecipientAddress: {}", messageModel.getRecipientAddress());
        if(messageModel.getRecipientAddress().equals("test")) {
            log.debug("RecipientAddress is test");
            email.setTo(email.getFrom());
            log.debug("email.getTo: {}", email.getTo());
        }

        email.setTo(messageModel.getRecipientAddress());
        email.setText(messageModel.getText());
        email.setSubject(messageModel.getSubject());

        log.info("Email: {}", email);

        try {
            mailSender.send(email);
        } catch (MailException e) {
            log.error("Can't send message: {}", e.getMessage());
        }

    }


}
