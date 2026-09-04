package com.example.metric_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
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
