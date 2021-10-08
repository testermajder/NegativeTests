package com.projectname.api.client.utils;

import java.io.Serializable;

public class BasicAuth implements Serializable {

    private String userName;

    private String password;

    public BasicAuth(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    private void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}
