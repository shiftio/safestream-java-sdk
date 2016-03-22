package com.safestream.sdk.api.watermarking;

public class WatermarkAPIException extends  Exception {

    public WatermarkAPIException() { }

    public WatermarkAPIException(String message) {
        super(message);
    }

    public WatermarkAPIException(String message, Throwable cause) {
        super(message, cause);
    }

    public WatermarkAPIException(Throwable cause) {
        super(cause);
    }

    public WatermarkAPIException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
