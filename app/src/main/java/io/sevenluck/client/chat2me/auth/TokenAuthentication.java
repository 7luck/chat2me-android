package io.sevenluck.client.chat2me.auth;

import java.io.Serializable;

/**
 * Created by loki on 6/7/16.
 */
public class TokenAuthentication implements Serializable {

    private String token;

    private static TokenAuthentication INSTANCE = null;

    private TokenAuthentication() {}

    public static synchronized TokenAuthentication getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new TokenAuthentication();
        }

        return INSTANCE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
