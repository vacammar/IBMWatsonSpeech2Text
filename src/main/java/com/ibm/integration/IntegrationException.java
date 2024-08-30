package com.ibm.integration;

public class IntegrationException extends Exception {

    public IntegrationException(Throwable cause) {
        super(cause);
    }

    public IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
