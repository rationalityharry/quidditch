package ru.quidditch.webapp.utils.rest;

public class FailResponse<T> extends RestResponse<T> {

    public FailResponse(T data) {
        super(false);
        this.setData(data);
    }
}