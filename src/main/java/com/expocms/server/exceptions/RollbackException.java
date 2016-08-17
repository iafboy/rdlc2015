package com.expocms.server.exceptions;

/**
 * Created by Samuel on 2015/10/6.
 */
public class RollbackException extends RuntimeException {
    public RollbackException() {
    }

    public RollbackException(String message) {
        super(message);
    }
}
