package com.suyh.exception;

/**
 * 自定义异常
 */
public class SuyhException extends RuntimeException {
    public SuyhException() {
    }

    public SuyhException(String message) {
        super(message);
    }
}
