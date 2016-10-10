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
     * parcelled errorcode has an unique settled code
     * @param errorCode error code
     */
    public Error(int errorCode) {
        mErrorCode = errorCode;
    }

    /**
     * add error message by yourself
     * @param errorCode error code
     * @param message   error message
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
