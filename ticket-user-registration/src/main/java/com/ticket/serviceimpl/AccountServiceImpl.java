package com.ticket.serviceimpl;


import com.ticket.entities.account.Account;
import com.ticket.exceptions.EmailExistsException;
import com.ticket.repositories.account.AccountRepository;
import com.ticket.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
    public Account registerNewUser(Account account) throws EmailExistsException {

        log.info("RegisterNewUser: {}", account);
        if (emailExist(account.getEmail())) {
            log.info("Email already exists");
            throw new EmailExistsException("Account with {} already exists " + account.getEmail());
        }

        log.info("User status false");
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setEnabled(false);
        log.info("User save to DB {}", account);
        account = repository.save(account);

        return account;
    }

    private boolean emailExist(String email) {
        final Account account = repository.findByLoginEmail(email).get();
        return (account != null) ? true : false;
    }

    @Override
    public Account updateExistingUser(Account account) throws EmailExistsException {
        log.info("UpdateExists User: {}", account.toString());
        final Long id = account.getId();
        final String email = account.getEmail();
        final Account emailOwner = repository.findByLoginEmail(email).get();
        if (emailOwner != null && !id.equals(emailOwner.getId())) {
            log.info("Email {} not available", account.getEmail());
            throw new EmailExistsException("Email not available.");
        }
        log.info("Save Update");
        return repository.save(account);
    }


    @Override
    public void saveRegisteredUser(final Account account)
    {
        log.info("SaveRegistered User {}", account);
        account.setEnabled(true);
        log.info("Change status to active");
        repository.save(account);
    }

    @Override
    public Account findUserByEmail(final String email) {
        log.info("Get User by email {}", email);
        return repository.findByLoginEmail(email).get();
    }



    @Override
    public void changeUserPassword(Account account, String password) {
        log.info("Change password on {} for user {}", password, account);
        account.setPassword(passwordEncoder.encode(password));
        log.info("User password after encoding: {}", account.getPassword());
        repository.save(account);
        log.info("User password saved to db");
    }

    @Override
    public Account save(Account account) {
        log.info("User save {}", account.toString());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        log.info("User password after encoding: {}", account.getPassword());
        return repository.save(account);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Delete User by Id: {}", id);
        repository.deleteById(id);
    }

    @Override
    public Iterable<Account> findAll() {
        return repository.findAll();
    }
}
