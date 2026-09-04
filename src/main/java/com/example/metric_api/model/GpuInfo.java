package com.example.metric_api.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GpuInfo {
    private String gpuName;
    private String vendor;
    private Long vram;
    private String deviceId;
    private String version;
}
