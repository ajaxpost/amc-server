package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String message;
    private T data;

}
