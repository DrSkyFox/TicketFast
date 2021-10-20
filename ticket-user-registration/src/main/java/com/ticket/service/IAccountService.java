package com.ticket.service;


import com.ticket.entities.account.Account;

import com.ticket.exceptions.EmailExistsException;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    Optional<Account> registerNewUser(Account account) throws EmailExistsException;

    Optional<Account> updateExistingUser(Account account) throws EmailExistsException;

    Optional<Account> findUserByEmail(final String email);


    void saveRegisteredUser(Account account);

    void changeUserPassword(Account account, String password);

    List<Account> findAll();

    void deleteById(Long id);
}

