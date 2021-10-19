package com.ticket.serviceimpl;

import com.ticket.entities.account.Account;
import com.ticket.entities.account.security.VerificationToken;
import com.ticket.repositories.account.VerificationTokenRepository;
import com.ticket.service.ITokenVerifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Slf4j
@Service
public class TokenVerifyServiceImpl implements ITokenVerifyService {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public TokenVerifyServiceImpl(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    public void createVerificationTokenForUser(final Account account, final String token) {
        log.info("VerificationToken saving");
        final VerificationToken myToken = new VerificationToken(token, account);
        try {
            verificationTokenRepository.save(myToken);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        log.info("VerificationToken saved");
    }

    @Override
    public VerificationToken getVerificationToken(final String token) {
        log.info("Get VerificationToken by token {}", token);
        return verificationTokenRepository.findByToken(token).get();
    }
}
