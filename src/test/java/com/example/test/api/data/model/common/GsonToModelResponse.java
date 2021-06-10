package com.example.test.api.data.model.common;

// Use to create Gson models
public class GsonToModelResponse<T> {
    private T obj;

    public GsonToModelResponse(T obj) {
        this.obj = obj;
    }

    public T getData() { return this.obj; }
}
