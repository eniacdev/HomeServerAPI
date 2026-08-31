package com.example.metric_api.model;

import com.example.metric_api.dto.CpuMetricDto;
import com.example.metric_api.dto.DiskMetricDto;
import com.example.metric_api.dto.MemoryMetricDto;
import com.example.metric_api.dto.NetworkMetricDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentMetric {
    private CpuMetricDto cpuMetric;
    private MemoryMetricDto memoryMetric;
    private DiskMetricDto diskMetric;
    private NetworkMetricDto networkMetric;
}
