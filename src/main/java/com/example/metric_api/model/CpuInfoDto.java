package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuInfoDto {

    private String cpuName;
    private String vendor;
    private String family;
    private String model;
    private String stepping;
    private String cpuId;

}
