package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HttpInfo {
    private String businessId;
    private String method;
    private String requestText;
    private String responseContentType;
    private String responseText;
    private Integer status;
    private String statusText;
    private String url;
}
