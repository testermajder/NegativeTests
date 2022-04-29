package com.projectname.api.client.data.model.users;


import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.projectname.api.client.data.model.crocodile.RequiredFieldErrorResponse;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class RequiredFieldErrorReponseUsers implements Serializable {

    @SerializedName("username")
    @Expose
    private List<String> username = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("password")
    @Expose
    private List<String> password = null;
    private final static long serialVersionUID = -7478006017787823214L;

    public RequiredFieldErrorReponseUsers() {
    }

    public static RequiredFieldErrorReponseUsers prepareErrorForUsername(List<String> username) {
        RequiredFieldErrorReponseUsers errorResponse = new RequiredFieldErrorReponseUsers();
        errorResponse.setUsername(username);
        return errorResponse;
    }

    public static RequiredFieldErrorReponseUsers prepareErrorForEmail(List<String> email) {
        RequiredFieldErrorReponseUsers errorResponse = new RequiredFieldErrorReponseUsers();
        errorResponse.setEmail(email);
        return errorResponse;
    }

    public static RequiredFieldErrorReponseUsers prepareErrorForPassword(List<String> password) {
        RequiredFieldErrorReponseUsers errorResponse = new RequiredFieldErrorReponseUsers();
        errorResponse.setPassword(password);
        return errorResponse;
    }
    public RequiredFieldErrorReponseUsers(List<String> username) {
        super();
        this.username = username;
    }

    public RequiredFieldErrorReponseUsers(List<String> username, List<String> email, List<String> password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

}