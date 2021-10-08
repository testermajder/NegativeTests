package com.projectname.api.client.data.model.users.byid;

import com.google.gson.annotations.SerializedName;
import com.projectname.api.client.annotations.ResponseRequiredField;
import com.projectname.api.client.data.model.users.common.Data;
import com.projectname.api.client.data.model.users.common.Support;

import java.io.Serializable;

//Model of response created with online tool http://www.jsonschema2pojo.org/
public class GetUserByIdResponse implements Serializable {

    private final static long serialVersionUID = -6694416097884854936L;
    @SerializedName("data")
    // our custom annotation to mark this field as required - used for ResponseSchemaValidation tests
    @ResponseRequiredField(canBeEmpty = false)
    private Data data;
    @SerializedName("support")
    @ResponseRequiredField(canBeEmpty = false)
    private Support support;

    public GetUserByIdResponse() {
    }

    public GetUserByIdResponse(Data data, Support support) {
        super();
        this.data = data;
        this.support = support;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

}
