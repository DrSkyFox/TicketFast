package com.ticket.exceptions;

public class OrganizationException extends RuntimeException{

    public OrganizationException() {
    }

    public OrganizationException(String message) {
        super(message);
    }
}
