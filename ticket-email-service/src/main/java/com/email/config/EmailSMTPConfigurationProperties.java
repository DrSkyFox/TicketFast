package com.email.config;


import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Getter
@ToString
@Configuration
public class EmailSMTPConfigurationProperties {

    private String mailServerHost;

    private Integer mailServerPort;

    private String mailServerUsername;

    private String mailServerPassword;

    private Boolean mailServerAuth;

    private String mailServerStartTls;

    private Boolean debugMode;

    private String fromAddress;


    @Value("${spring.mail.host}")
    public void setMailServerHost(String mailServerHost) {
        if(mailServerHost == null) {
            mailServerHost = "locahost";
        }
        this.mailServerHost = mailServerHost;
    }

    @Value("${spring.mail.port}")
    public void setMailServerPort(Integer mailServerPort) {
        if(mailServerPort == null) {
            mailServerPort= 587;
        }
        this.mailServerPort = mailServerPort;
    }

    @Value("${spring.mail.username}")
    public void setMailServerUsername(String mailServerUsername) {
        this.mailServerUsername = mailServerUsername;
    }

    @Value("${spring.mail.password}")
    public void setMailServerPassword(String mailServerPassword) {
        this.mailServerPassword = mailServerPassword;
    }

    @Value("${spring.mail.properties.mail.smtp.auth}")
    public void setMailServerAuth(Boolean mailServerAuth) {
        this.mailServerAuth = mailServerAuth;
    }

    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    public void setMailServerStartTls(String mailServerStartTls) {
        this.mailServerStartTls = mailServerStartTls;
    }

    @Value("${spring.mail.properties.mail.debug}")
    public void setDebugMode(Boolean debugMode) {
        if(debugMode == null) {
            debugMode = false;
        }
        this.debugMode = debugMode;
    }

    @Value("${spring.mail.fromAddress}")
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }
}
