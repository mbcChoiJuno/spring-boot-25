package com.mbc.juno.core;

import lombok.Data;

@Data
public class ApiResult<T> {
    private boolean isSuccess = true;
    private int resCode;
    private String resMessage;
    private T data;

    public ApiResult() {

    }

    public ApiResult(T data) {
        this.data = data;
    }
}
