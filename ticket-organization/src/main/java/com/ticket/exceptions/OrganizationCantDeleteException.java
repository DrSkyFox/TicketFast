package com.ticket.exceptions;

public class OrganizationCantDeleteException extends RuntimeException{

    private static final String MSG_DEFAULT = "Organization cant delete.";

    public OrganizationCantDeleteException() {
    }

    public OrganizationCantDeleteException(String message) {
        super(MSG_DEFAULT + " " + message);
    }



}
