package com.ticket.config;


import com.ticket.exceptions.AccountNotFoundException;
import com.ticket.repositories.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class LssUserDetailsService implements UserDetailsService {

//    private static final String ROLE_USER = "ROLE_USER";

    private final AccountRepository accountRepository;

    @Autowired
    public LssUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

//    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
//        return Arrays.asList(new SimpleGrantedAuthority(role));
//    }

    @Override
    public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
        return accountRepository.findByLoginOrLoginEmail(loginOrEmail)
                .map(account -> new User(
                        account.getLogin(),
                account.getPassword(),
                account.getEnabled() ,
                account.getAccountNonExpired(),
                account.getAccountNonExpired(),
                account.getAccountNonLocked(),
                account.getAccountRoleRef().
                        stream().
                        map(reg ->new SimpleGrantedAuthority(reg.getRole().getName()))
                        .collect(Collectors.toList())
                )).orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }


}
