package com.ticket.repr;


import com.ticket.entities.account.Account;
import com.ticket.enums.AccountStatus;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Builder(toBuilder = true)
@Getter
@Setter(value = AccessLevel.PUBLIC)
@AllArgsConstructor
@NoArgsConstructor
public class AccountRepr {

    private Long id;

    @NotEmpty
    private String login;

    @Email
    @NotEmpty(message = "Email is required.")
    private String email;

    private String phoneNumber;

    @NotEmpty(message = "Password is required.")
    private String password;

    @NotEmpty(message = "Password confirmation is required.")
    private String confirmPassword;

    private AccountStatus status;

    private Boolean enabled;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;


    public AccountRepr(Account account) {
        this.id = account.getId();
        this.login = account.getLogin();
        this.email = account.getEmail();
        this.phoneNumber = account.getPhoneNumber();
        this.status = account.getStatus();
        this.enabled = account.getEnabled();
        this.accountNonExpired = account.getAccountNonExpired();
        this.accountNonLocked = account.getAccountNonLocked();
        this.credentialsNonExpired = account.getCredentialsNonExpired();
    }
}
