package com.safestream.sdk.http;

public class SafeStreamHttpClientException extends Exception {

    public SafeStreamHttpClientException() {
    }

    public SafeStreamHttpClientException(String message) {
        super(message);
    }

    public SafeStreamHttpClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SafeStreamHttpClientException(Throwable cause) {
        super(cause);
    }

    public SafeStreamHttpClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
