package com.safestream.sdk.api.video;

public class VideoAPIException extends  Exception {

    public VideoAPIException() {
    }

    public VideoAPIException(String message) {
        super(message);
    }

    public VideoAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public VideoAPIException(Throwable cause) {
        super(cause);
    }

    public VideoAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
