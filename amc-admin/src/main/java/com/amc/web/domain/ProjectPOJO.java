package com.amc.web.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class ProjectPOJO {

    private String project_id;
    private String project_name;
    private String project_desc;
    private String create_time;

}
