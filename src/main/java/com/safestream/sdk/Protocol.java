package com.safestream.sdk;

import java.lang.String;

public enum Protocol {

    HTTP("http"),
    HTTPS("https");

    private String name;

    Protocol(String stringVal) {
        name = stringVal;
    }

    public String toString(){
        return name;
    }

    public static String getEnumByString(String code){
        for(Protocol e : Protocol.values()){
            if(e.name.equals(code)) return e.name();
        }
        return null;
    }
}
