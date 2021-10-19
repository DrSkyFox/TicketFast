package com.ticket.serviceimpl;

import com.ticket.entities.account.Account;
import com.ticket.entities.account.security.PasswordResetToken;
import com.ticket.repositories.account.PasswordResetTokenRepository;
import com.ticket.service.ITokenResetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenResetServiceImpl implements ITokenResetService {

    private final PasswordResetTokenRepository passwordTokenRepository;

    @Autowired
    public TokenResetServiceImpl(PasswordResetTokenRepository passwordTokenRepository) {
        this.passwordTokenRepository = passwordTokenRepository;
    }

    @Override
    public void createPasswordResetTokenForUser(Account account, String token) {
        log.info("Creating reset token in DB. Token is  {}", token);
        final PasswordResetToken myToken = new PasswordResetToken(token, account);
        log.info("Saving token to DB");
        passwordTokenRepository.save(myToken);
    }

    @Override
    public PasswordResetToken getPasswordResetToken(String token)
    {
        log.info("Get PasswordResetToken by token {}", token);
        return passwordTokenRepository.findByToken(token).get();
    }
}
