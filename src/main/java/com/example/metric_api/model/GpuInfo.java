package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GpuInfo {
    private String gpuName;
    private String vendor;
    private Long vram;
    private String deviceId;
    private String version;
}
