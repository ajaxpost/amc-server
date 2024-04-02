package com.amc.core;

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
public class TableDataInfo implements Serializable {

    private Long total;

    private List<?> data;

    private int code;

    private String message;

}
