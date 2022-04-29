package com.projectname.api.client.data.model.users.update;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse implements Serializable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    private final static long serialVersionUID = 2464046007452784537L;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(String name, String job, String updatedAt) {
        super();
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public static UpdateUserResponse parseExpectedUserResponse(UpdateUserRequest userRequest) {
        UpdateUserResponse updateUserResponse = new UpdateUserResponse();
        updateUserResponse.setJob(userRequest.getJob());
        updateUserResponse.setName(userRequest.getName());
        return updateUserResponse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
