package com.ticket.exceptions;

public class OrganizationCantDeleteException extends RuntimeException{

    public OrganizationCantDeleteException() {
    }

    public OrganizationCantDeleteException(String message) {
        super(message);
    }
}
