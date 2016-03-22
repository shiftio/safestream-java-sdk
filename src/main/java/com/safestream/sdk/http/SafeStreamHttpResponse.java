package com.safestream.sdk.http;

import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class SafeStreamHttpResponse {

    private int httpStatus;
    private String body;

    public SafeStreamHttpResponse() {}

    public SafeStreamHttpResponse(String body) {
        this.body = body;
    }

    public SafeStreamHttpResponse(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public SafeStreamHttpResponse(int httpStatus, String body) {
        this.httpStatus = httpStatus;
        this.body = body;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Deserializes body to object of type E
     * @param clazz Target class to deserialize to
     * @param <E>
     * @return An object of type E
     */
    public <E> E getEntity(Class<E> clazz) {
        if(this.getBody() == null || this.getBody().isEmpty()) {
            throw new SafeStreamHttpResponseException("No response body exists. Cannot deserialize to " + clazz.getName());
        }
        return new GsonBuilder().create().fromJson(this.getBody(), clazz);
    }

    public <E> E getEntity(Type type) {
        if(this.getBody() == null || this.getBody().isEmpty()) {
            throw new SafeStreamHttpResponseException("No response body exists. Cannot deserialize to " + type.toString());
        }
        return new GsonBuilder().create().fromJson(this.getBody(), type);
    }
}
