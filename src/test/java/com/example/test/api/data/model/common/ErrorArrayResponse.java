package com.example.test.api.data.model.common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class ErrorArrayResponse implements Serializable, ResponseHelper
{

    @SerializedName("field")
    @Expose
    private String field;
    @SerializedName("message")
    @Expose
    private String message;

    private final static long serialVersionUID = 3132889488126774428L;

    public ErrorArrayResponse() {
    }

    public ErrorArrayResponse(String field, String message) {
        super();
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorArrayMessage() {
        return message;
    }

    public void setErrorArrayMessage(String message) {
        this.message = message;
    }

    public String getErrorResponseToString() {
        return new ToStringBuilder(this).append("field", field).append("message", message).toString();
    }

    @Override
    public boolean isResponseNull() {
        return field == null & message == null;
    }
}