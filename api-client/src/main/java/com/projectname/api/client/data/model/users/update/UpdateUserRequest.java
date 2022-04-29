package com.projectname.api.client.data.model.users.update;

import com.projectname.api.client.data.model.users.create.CreateUserRequest;

import java.io.Serializable;

public class UpdateUserRequest extends CreateUserRequest implements Serializable {

    public UpdateUserRequest() {
        super();
    }

    public UpdateUserRequest(String name, String job) {
        super(name, job);
    }

}
