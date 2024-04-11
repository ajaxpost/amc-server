package com.amc.web.domain;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
enum Status {
    SUCCESS(1),
    FAIL(0);

    private int status;

    Status(int status) {
        this.status = status;
    }


}

@Data
@ToString
@ApiModel(value = "ResourcePOJO", description = "资源信息")
@Slf4j
public class ResourcePOJO {
    private String apiKey;
    private String city;
    private String province;
    private String ip;
    private String elementType;
    private String pageUrl;
    private String routeId;
    private String sdkVersion;
    private String sesId;
    private String size;
    private String sourceUrl;
    private String userId;
    private Double duration;
    private Double startTime;
    private Status status;
    private Long time;
    private DeviceInfo deviceInfo;
    private Boolean isCache;

    public int getStatus() {
        return status.getStatus();
    }

    public void setStatus(int status) {
        for (Status s : Status.values()) {
            if (s.getStatus() == status) {
                this.status = s;
                break;
            }
        }
        if (this.status == null) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }

    }
}