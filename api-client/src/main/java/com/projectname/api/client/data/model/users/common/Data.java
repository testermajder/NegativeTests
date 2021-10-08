package com.projectname.api.client.data.model.users.common;

import com.google.gson.annotations.SerializedName;
import com.projectname.api.client.annotations.ResponseRequiredField;

import java.io.Serializable;

//Model of response created with online tool http://www.jsonschema2pojo.org/
public class Data implements Serializable {

    private final static long serialVersionUID = -2659014833864276105L;
    @SerializedName("id")
    @ResponseRequiredField(canBeEmpty = false)
    private Integer id;
    @SerializedName("email")
    @ResponseRequiredField(canBeEmpty = false)
    private String email;
    @SerializedName("first_name")
    @ResponseRequiredField(canBeEmpty = false)
    private String firstName;
    @SerializedName("last_name")
    @ResponseRequiredField(canBeEmpty = false)
    private String lastName;
    @SerializedName("avatar")
    @ResponseRequiredField(canBeEmpty = false)
    private String avatar;

    public Data() {
    }

    public Data(Integer id, String email, String firstName, String lastName, String avatar) {
        super();
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}
