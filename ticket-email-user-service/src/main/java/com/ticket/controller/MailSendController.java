package com.ticket.controller;


import com.ticket.model.MessageModelRef;
import com.ticket.service.MailServiceQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/mail")
public class MailSendController {

    private final MailServiceQuery serviceQuery;

    @Autowired
    public MailSendController(MailServiceQuery serviceQuery) {
        this.serviceQuery = serviceQuery;
    }

    @RequestMapping
    public ModelAndView createMessageEmail(final HttpServletRequest request) {
        log.info("Connection to mail. Request Info: serverName - {}, serverPort - {}, requestContext - {}",
                request.getServerName(), request.getServerPort(), request.getContextPath());
        return new ModelAndView("mail_page", "message", new MessageModelRef());
    }

    @RequestMapping("/doSend")
    public ModelAndView doSend(@Valid final MessageModelRef messageMailRef, final BindingResult result, final HttpServletRequest request) {

        log.info("Send Message: messageModel {}; Errors: {}", messageMailRef, result.hasErrors());
        if(result.hasErrors()) {
            log.error("Return to page with errors");
            return new ModelAndView("mail_page", "message", messageMailRef);
        }
        log.info("Send to query");
        serviceQuery.send(messageMailRef.getMessageMail());
        log.info("Redirect to /mail");
        return new ModelAndView("redirect:/mail");
    }




}
