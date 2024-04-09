package com.amc.web.domain.maptype;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MinuteDataType {
    private String minute;
    private Integer count;
}
