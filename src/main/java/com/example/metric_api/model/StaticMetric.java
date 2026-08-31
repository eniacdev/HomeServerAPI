package com.example.metric_api.model;

import com.example.metric_api.dto.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StaticMetric {
    private CpuInfoDto cpuInfo;
    private OsInfoDto osInfo;
    private NetworkInfoDto networkInfo;
    private BiosInfoDto biosInfo;
    private MotherBoardInfoDto motherBoardInfo;
}
