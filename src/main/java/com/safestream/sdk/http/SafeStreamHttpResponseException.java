package com.safestream.sdk.http;

public class SafeStreamHttpResponseException extends RuntimeException {

    public SafeStreamHttpResponseException() {
    }

    public SafeStreamHttpResponseException(String message) {
        super(message);
    }

    public SafeStreamHttpResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public SafeStreamHttpResponseException(Throwable cause) {
        super(cause);
    }

    public SafeStreamHttpResponseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
