package com.ticket.exceptions;

public class OrganizationExistsException extends RuntimeException {

    private static final String MSG_DEFAULT = "Name of Organization already exists";

    public OrganizationExistsException() {
        super(MSG_DEFAULT);
    }

    public OrganizationExistsException(String message) {
        super(message);
    }



}
