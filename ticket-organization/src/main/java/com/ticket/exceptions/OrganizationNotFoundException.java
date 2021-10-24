package com.ticket.exceptions;

public class OrganizationNotFoundException extends RuntimeException {
    public OrganizationNotFoundException() {
    }

    public OrganizationNotFoundException(String message) {
        super(message);
    }



}
