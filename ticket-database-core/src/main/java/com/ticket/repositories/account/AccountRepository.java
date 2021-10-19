package com.ticket.repositories.account;

import com.ticket.entities.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {


    @Query("select a from Account a where a.email = (:loginEmail) and a.enabled = true")
    Optional<Account> findByLoginEmail(@Param("loginEmail") String loginEmail);

    @Query("select a from Account a where a.login = (:login) and a.enabled = true")
    Optional<Account> findByLogin(String login);

    @Query("select a from Account a where a.email = (:loginOrEmail) or a.login = (:loginOrEmail)")
    Optional<Account> findByLoginOrLoginEmail(@Param("loginOrEmail") String loginOrEmail);

    void deleteAccountById(Long id);

    @Query("select a from Account a where a.enabled = true")
    List<Account> findAll();


}
