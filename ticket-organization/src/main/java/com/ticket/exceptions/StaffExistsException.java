package com.ticket.exceptions;

public class StaffExistsException extends RuntimeException {


    public StaffExistsException() {
    }

    public StaffExistsException(String message) {
        super(message);
    }
}
