package ru.quidditch.webapp.utils.response;

public class FailResponse<T> extends RestResponse<T> {

    public FailResponse(T data) {
        super(false);
        this.setData(data);
    }
}