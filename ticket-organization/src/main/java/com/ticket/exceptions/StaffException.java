package com.ticket.exceptions;

public class StaffException extends RuntimeException{

    public StaffException() {
    }

    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Throwable cause) {
        super(message, cause);
    }
}
