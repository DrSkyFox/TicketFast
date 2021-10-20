package com.ticket.controller;


import com.google.common.collect.ImmutableMap;
import com.ticket.exceptions.EmailExistsException;
import com.ticket.exceptions.TokenExistsException;
import com.ticket.exceptions.TokenTimeException;
import com.ticket.exceptions.TokenValidException;
import com.ticket.representative.AccountRepr;
import com.ticket.service.IAccountFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@Controller()
public class RegistrationController {

    private final IAccountFacadeService service;

    @Autowired
    public RegistrationController(IAccountFacadeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/signup")
    public ModelAndView registrationForm() {
        return new ModelAndView("registrationPage", "account", new AccountRepr());
    }

    @RequestMapping(value = "/reg")
    public ModelAndView registerUser(@Valid final AccountRepr account, final BindingResult result, final HttpServletRequest request) {
        log.info("Request Registration New User");

        if (result.hasErrors()) {
            return new ModelAndView("registrationPage", "account", account);
        }
        
        try {
            service.registerAccount(account, request);
        } catch (EmailExistsException e) {
            result.addError(new FieldError("account", "email", e.getMessage()));
            return new ModelAndView("registrationPage", "account", account);
        }
        return new ModelAndView("redirect:/login");
    }


    @RequestMapping(value = "/account/registrationConfirm")
    public ModelAndView confirmRegistration(@RequestParam("token") final String token,
                                            final RedirectAttributes redirectAttributes) {
        log.info("Confirmation request with incoming token value: {}", token);
        try {
            service.confirmRegistration(token);
            redirectAttributes.addFlashAttribute("message", "Your account verified successfully");
        } catch (TokenExistsException | TokenTimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return new ModelAndView("redirect:/login");

    }


    //FORGOT PASSWORD

    @RequestMapping(value = "/account/resetPassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView resetPassword(
            final HttpServletRequest request,
            @RequestParam("email") final String loginEmail,
            final RedirectAttributes redirectAttributes
    ) {

        try {
            service.resetPassword(loginEmail, request);
            redirectAttributes.addFlashAttribute("message", "You should receive an Password Reset Email shortly");
        } catch (TokenExistsException | TokenTimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return new ModelAndView("redirect:/login");

    }

    @RequestMapping(value = "/account/changePassword", method = RequestMethod.GET)
    public ModelAndView showChangePasswordPage(@RequestParam("id") final long id,
                                               @RequestParam("token") final String token,
                                               final RedirectAttributes redirectAttributes) {
        try {
            service.changeAccountPassword(id, token);
        } catch (TokenExistsException | TokenValidException | TokenTimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return new ModelAndView("redirect:/login");
        }
        final ModelAndView view = new ModelAndView("resetPassword");
        view.addObject("token", token);
        view.addObject("id", token);
        return view;
    }

    @RequestMapping(value = "/account/savePassword", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView savePassword(@RequestParam("password") final String password,
                                     @RequestParam("passwordConfirmation") final String passwordConfirmation,
                                     @RequestParam("token") final String token,
                                     @RequestParam("id") final Long id,
                                     final RedirectAttributes redirectAttributes) {
        if (!password.equals(passwordConfirmation)) {
            return new ModelAndView("resetPassword", ImmutableMap.of("errorMessage", "Passwords do not match"));
        }
        try {
            service.savePassword(id, token, password);
            redirectAttributes.addFlashAttribute("message", "Password reset successfully");
        } catch (TokenExistsException | TokenValidException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return new ModelAndView("redirect:/login");

    }


}
