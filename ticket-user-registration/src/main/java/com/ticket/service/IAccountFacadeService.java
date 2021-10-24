package com.ticket.service;

import com.ticket.entities.account.Account;
import com.ticket.exceptions.EmailExistsException;
import com.ticket.exceptions.TokenExistsException;
import com.ticket.exceptions.TokenTimeException;
import com.ticket.repr.AccountRepr;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface IAccountFacadeService {


    Optional<AccountRepr> registerAccount(AccountRepr account, HttpServletRequest request) throws EmailExistsException;

    Optional<AccountRepr> updateExistingUser(Account account) throws EmailExistsException;

    Optional<AccountRepr> confirmRegistration(String token) throws TokenTimeException, TokenExistsException;

    Optional<AccountRepr> resetPassword(String emailOrLogin, HttpServletRequest request);

    void changeAccountPassword(Long id, String token);

    void savePassword(Long id, String token, String password);



}
