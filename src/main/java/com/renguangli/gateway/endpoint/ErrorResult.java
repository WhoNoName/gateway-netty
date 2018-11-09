package com.renguangli.gateway.endpoint;

/**
 * ErrorResult
 *
 * @author renguangli 2018/11/8 14:19
 * @since JDK 1.8
 */
public class ErrorResult {

    private String error;

    private String message;

    public ErrorResult(){}

    public ErrorResult(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorResult{" +
                "error=" + error +
                ", message=" + message +
                "}";
    }

}
