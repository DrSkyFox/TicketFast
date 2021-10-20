package com.ticket.serviceimpl;


import com.ticket.entities.account.Account;
import com.ticket.enums.AccountStatus;
import com.ticket.exceptions.EmailExistsException;
import com.ticket.repositories.account.AccountRepository;
import com.ticket.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AccountServiceImpl implements IAccountService {


    private final AccountRepository repository;

    private final PasswordEncoder passwordEncoder;



    @Autowired
    public AccountServiceImpl(AccountRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public Optional<Account> registerNewUser(Account account) throws EmailExistsException {

        log.info("RegisterNewUser: {}", account);
        if (emailExist(account.getEmail())) {
            log.info("Email already exists");
            throw new EmailExistsException("Account with {} already exists " + account.getEmail());
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        log.info("Encoding password account {}", account.getLogin());
        account.setEnabled(false);
        account.setStatus(AccountStatus.WAIT_CONFIRM);
        log.info("Account save to DB {}", account);
        return Optional.of(repository.save(account));
    }

    private boolean emailExist(String email) {
        final Optional<Account> account = repository.findByLoginEmail(email);
        return account.isPresent();
    }

    @Override
    @Transactional
    public Optional<Account> updateExistingUser(Account account) throws EmailExistsException {
        log.info("UpdateExists User: {}", account.toString());
        final Long id = account.getId();
        final String email = account.getEmail();
        final Optional<Account> emailOwner = repository.findByLoginEmail(email);
        if (emailOwner.isPresent() && !id.equals(emailOwner.get().getId())) {
            log.info("Email {} not available", account.getEmail());
            throw new EmailExistsException("Email not available.");
        }
        log.info("Save Update");
        return Optional.of(repository.save(account));
    }


    @Override
    @Transactional
    public void saveRegisteredUser(final Account account)
    {
        log.info("SaveRegistered User {}", account);
        account.setEnabled(true);
        log.info("Change status to active");
        repository.save(account);
    }

    @Override
    public Optional<Account> findUserByEmail(final String email) {
        log.info("Get User by email {}", email);
        return repository.findByLoginEmail(email);
    }



    @Override
    @Transactional
    public void changeUserPassword(Account account, String password) {
        log.info("Change password on {} for user {}", password, account);
        account.setPassword(passwordEncoder.encode(password));
        log.info("User password after encoding: {}", account.getPassword());
        repository.save(account);
        log.info("User password saved to db");
    }



    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Delete User by Id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }




}
