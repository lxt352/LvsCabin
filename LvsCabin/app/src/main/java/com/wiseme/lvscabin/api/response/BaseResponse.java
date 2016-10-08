package com.wiseme.lvscabin.api.response;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class BaseResponse {

    String message;

    int status;

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
