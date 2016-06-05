package io.sevenluck.client.chat2me.domain;

import java.io.Serializable;

/**
 * Created by loki on 6/5/16.
 */
public class RestException implements Serializable {

    private String message;
    private String type;

    public RestException() {
    }

    public RestException(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
