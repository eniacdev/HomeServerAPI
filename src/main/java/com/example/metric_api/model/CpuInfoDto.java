package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuInfoDto {

    private String cpuName;
    private Integer physicalCore;
    private Integer logicalCore;
    //private Long maxFreqMHz;
    private Boolean is64Bit;

}
