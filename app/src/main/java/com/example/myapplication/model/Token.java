package com.example.myapplication.model;

/**
 * Class access token
 */
public class Token {
    String token;
    public Token(String token) {
        this.token = token;
    }

    /**
     * return access token
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * set the access token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }
}
