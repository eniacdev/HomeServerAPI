package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GpuInfoDto {
    private String gpuName;
    private String vendor;
    private Long vram;
    private String vramFormatted;
    private String deviceId;
    private String version;
}
