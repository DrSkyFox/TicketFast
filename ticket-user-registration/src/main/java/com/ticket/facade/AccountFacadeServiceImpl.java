package com.ticket.facade;


import com.ticket.entities.account.Account;
import com.ticket.entities.account.security.PasswordResetToken;
import com.ticket.entities.account.security.VerificationToken;
import com.ticket.events.OnForgotPasswordEvent;
import com.ticket.events.OnRegistrationCompleteEvent;
import com.ticket.exceptions.*;
import com.ticket.representative.AccountRepr;
import com.ticket.service.IAccountFacadeService;
import com.ticket.service.IAccountService;
import com.ticket.service.ITokenResetService;
import com.ticket.service.ITokenVerifyService;
import com.ticket.utils.Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AccountFacadeServiceImpl implements IAccountFacadeService {

    private final IAccountService accountService;
    private final ITokenVerifyService verifyService;
    private final ITokenResetService resetService;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public AccountFacadeServiceImpl(IAccountService accountService, ITokenVerifyService verifyService, ITokenResetService resetService, ApplicationEventPublisher eventPublisher) {
        this.accountService = accountService;
        this.verifyService = verifyService;
        this.resetService = resetService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Optional<AccountRepr> registerAccount(AccountRepr account, HttpServletRequest request) throws EmailExistsException {

        account.setEnabled(false);
        log.info("New Account is {}", account);
        Optional<Account> register = accountService.registerNewUser(Converter.converterAccountRepr(account));
        if(register.isPresent()) {
            log.info("Saved to DB: {}", register.get());
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(register.get(), getUrlReg(request)));
        }
        return Optional.of(Converter.converterAccount(register.orElse(null)));
    }

    @Override
    public Optional<AccountRepr> confirmRegistration(String token) throws TokenTimeException, TokenExistsException {

        final VerificationToken verificationToken = verifyService.getVerificationToken(token);
        if (verificationToken == null) {
            log.info("Token not found in DB");
            throw new TokenExistsException("Token not found");
        }

        final Calendar cal = Calendar.getInstance();
        if (verificationToken.getExpiryDate().getTime() - cal.getTime().getTime() <= 0) {
            log.info("Token {} expired", token);
            throw new TokenTimeException("Token expired");
        }

        log.info("Token found. Check exists account");
        final Account account = verificationToken.getAccount();

        switch (account.getStatus()) {
            case LOCKED -> {
                log.error("Account {} locked", account.getLogin());
                throw new AccountLockedException("AccountLocked");
            }
            case EXPIRED -> {
                log.error("Account {} registraion expired", account.getLogin());
                throw new AccountExpiredException("Registration time expired");
            }
        }

        log.info("Got user: id - {}, login - {} with token {}", account.getId(), account.getLogin(), token);

        account.setEnabled(true);
        log.info("User is active : {}", account.getEnabled());
        accountService.saveRegisteredUser(account);
        log.info("Saving to DB");

        return Optional.of(Converter.converterAccount(account));

    }


    @Override
    public Optional<AccountRepr> resetPassword(String loginOrEmail, HttpServletRequest request) {

        log.info("Account request reset password by email {}", loginOrEmail);
        final Optional<Account> account = accountService.findUserByEmail(loginOrEmail);
        log.info("User is {}", account.toString());
        if (account.isPresent()) {
            final String token = UUID.randomUUID()
                    .toString();
            log.info("Token for reset {}", token);
            resetService.createPasswordResetTokenForUser(account.get(), token);
            log.info("Token saved to DB");

            log.info("ForgotPassword event");
            eventPublisher.publishEvent(new OnForgotPasswordEvent(account.get(), getUrlReg(request)));
            log.info("event is published");
        }

        return Optional.of(Converter.converterAccount(account.orElse(null)));
    }

    @Override
    public void changeAccountPassword(Long id, String token) {
        final PasswordResetToken resetToken = resetService.getPasswordResetToken(token);
        if (resetToken == null) {
            throw new TokenExistsException("Token not found");
        }

        final Calendar cal = Calendar.getInstance();
        if ((resetToken.getExpiryDate()
                .getTime()
                - cal.getTime()
                .getTime()) <= 0) {
            throw new TokenTimeException("Token expired");
        }
        final Account account = resetToken.getAccount();
        if (account.getId() != id)
            throw new TokenValidException("Invalid password reset token");

    }

    @Override
    public void savePassword(Long id, String token, String password) {

        final PasswordResetToken p = resetService.getPasswordResetToken(token);
        if (p == null) {
            throw new TokenExistsException("token not found");
        }
        if(!p.getId().equals(id)) {
            throw new TokenValidException("Token is not valid");
        }
        accountService.changeUserPassword(p.getAccount(), password);
    }


    @Override
    public Optional<AccountRepr> updateExistingUser(Account account){

        return Optional.empty();
    }


    private String getUrlReg(final HttpServletRequest request) {
        final String appUrl = "http://"
                + request.getServerName()
                + ":" + request.getServerPort()
                + request.getContextPath();
        return appUrl;
    }

}
