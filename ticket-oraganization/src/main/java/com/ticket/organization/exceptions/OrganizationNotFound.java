package com.ticket.organization.exceptions;

public class OrganizationNotFound extends RuntimeException {
    public OrganizationNotFound() {
    }

    public OrganizationNotFound(String message) {
        super(message);
    }



}
