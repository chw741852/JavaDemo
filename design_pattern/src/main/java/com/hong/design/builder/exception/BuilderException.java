package com.hong.design.builder.exception;

/**
 * Created by Hongwei on 2015/11/26.
 */
public class BuilderException extends Exception {
    private static final long serialVersionUID = 5013602045774046492L;

    public BuilderException() {
    }

    public BuilderException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return message == null ? s : s + ": " + message;
    }
}
