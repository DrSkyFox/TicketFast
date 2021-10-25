package com.ticket.exceptions;

public class OrganizationNotFoundException extends RuntimeException {

    private static final String MSG_DEFAULT = "Organization not found";

    public OrganizationNotFoundException() {
        super(MSG_DEFAULT);
    }

    public OrganizationNotFoundException(String message) {
        super(message);
    }




}
