package ru.quidditch.webapp.utils.response;

public class SuccessResponse<T> extends RestResponse<T> {

    public SuccessResponse(T data) {
        super(true);
        this.setData(data);
    }
}