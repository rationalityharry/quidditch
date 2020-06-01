package ru.quidditch.webapp.utils.rest;

public abstract class RestResponse<T> {

    private final static String RESULT_OK = "ok";
    private final static String RESULT_ERROR = "error";

    private String result;

    private T data;

    public RestResponse(Boolean success) {
        if (success != null && success) {
            this.result = RESULT_OK;
        } else {
            this.result = RESULT_ERROR;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}