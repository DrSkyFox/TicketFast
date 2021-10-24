package com.ticket;

import com.ticket.entities.account.Account;
import com.ticket.repr.AccountRepr;


public class Converter {

    public static Account converterAccountRepr(AccountRepr accountRepr) {
        return Account.builder()
                .id(accountRepr.getId())
                .login(accountRepr.getLogin())
                .email(accountRepr.getEmail())
                .phoneNumber(accountRepr.getPhoneNumber())

                .status(accountRepr.getStatus())
                .password(accountRepr.getPassword())
                .status(accountRepr.getStatus())
                .build();
    }


    public static AccountRepr converterAccount(Account account) {
        return AccountRepr.builder()
                .id(account.getId())
                .login(account.getLogin())
                .email(account.getEmail())
                .phoneNumber(account.getPhoneNumber())
                .password(account.getPassword())
                .status(account.getStatus())
                .accountNonLocked(account.getAccountNonLocked())
                .accountNonExpired(account.getAccountNonExpired())
                .credentialsNonExpired(account.getCredentialsNonExpired())
                .enabled(account.getEnabled())
                .build();
    }



}
