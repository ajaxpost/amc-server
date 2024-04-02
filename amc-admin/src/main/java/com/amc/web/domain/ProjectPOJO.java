package com.amc.web.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProjectPOJO {

    private String projectId;
    private String projectName;
    private String projectDesc;
    private String createTime;


}
