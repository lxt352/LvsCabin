package com.wiseme.lvscabin.structure;

/**
 * Created by lvtoa
 * lxt352@gmail.com
 */

public class Error {

    public static final int ERROR_CODE_UNKNOW = 1000;

    public static final int ERROR_CODE_UNCONNECTED = 1001;

    private int mErrorCode;

    private String mMessage;

    /**
     * 封装好的错误代码有固定的错误信息
     *
     * @param errorCode 错误代码
     */
    public Error(int errorCode) {
        mErrorCode = errorCode;
    }

    /**
     * 手动添加错误信息
     *
     * @param errorCode 错误代码
     * @param message   错误信息
     */
    public Error(int errorCode, String message) {
        mErrorCode = errorCode;
        mMessage = message;
    }

    public static Error adapt(Throwable t) {
        String message = t.getMessage();
        if (message == null) {
            message = t.getCause().toString();
        }
        return new Error(ERROR_CODE_UNKNOW, message);
    }
}
