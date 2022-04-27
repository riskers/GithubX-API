package com.riskers.githubx.common;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author riskers
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -3346067868772257255L;

    private int code;
    private String message;
    private T data;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "success", data);
    }

    public static Result failure(String message) {
        return new Result(400, message, null);
    }
}
