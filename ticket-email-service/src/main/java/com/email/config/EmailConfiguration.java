package com.email.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.Properties;

@Configuration
public class EmailConfiguration {


    private final EmailSMTPConfigurationProperties smtpConfigurationProperties;

    @Autowired
    public EmailConfiguration(EmailSMTPConfigurationProperties smtpConfigurationProperties) {
        this.smtpConfigurationProperties = smtpConfigurationProperties;
    }

    @Bean
    public JavaMailSender getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(smtpConfigurationProperties.getMailServerHost());
        mailSender.setPort(smtpConfigurationProperties.getMailServerPort());
        mailSender.setUsername(smtpConfigurationProperties.getMailServerUsername());
        mailSender.setPassword(smtpConfigurationProperties.getMailServerPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", smtpConfigurationProperties.getMailServerAuth());
        props.put("mail.smtp.starttls.enable", smtpConfigurationProperties.getMailServerStartTls());
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return mailSender;
    }


    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("This is the test email template for your email:\n%s\n");
        return message;
    }

    @Bean
    public SpringTemplateEngine thymeleafTemplateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mailMessages");
        return messageSource;
    }
}