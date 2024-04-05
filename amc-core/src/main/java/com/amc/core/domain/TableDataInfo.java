package com.amc.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TableDataInfo<T> implements Serializable {

    private Long total;

    private List<T> data;

    private int code;

    private String message;

}
