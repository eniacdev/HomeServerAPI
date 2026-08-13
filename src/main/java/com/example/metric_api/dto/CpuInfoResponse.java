package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuInfoResponse {

    private String cpuName;
    private Integer physicalCore;
    private Integer logicalCore;
    //private Long maxFreqMHz;
    private Boolean is64Bit;

}
