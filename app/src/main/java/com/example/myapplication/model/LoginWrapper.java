package com.example.myapplication.model;

/**
 * class LoginWrapper
 */
public class LoginWrapper {
    private String email;
    private String password;
    public LoginWrapper(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * returns email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * set the email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * set the password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
