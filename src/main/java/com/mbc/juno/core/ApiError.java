package com.mbc.juno.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "isSuccess", "resCode", "resMessage" })
public class ApiError {
    @JsonProperty("isSuccess")
    private boolean isSuccess = false;
    private int resCode;
    private String resMessage;
}
