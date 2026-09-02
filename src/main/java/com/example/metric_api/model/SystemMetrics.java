package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetrics {
    private Long logId;
    private LocalDateTime createdAt;
    private CpuMetric cpuMetric;
    private MemoryMetric memoryMetric;
    private DiskMetric diskMetric;
    private NetworkMetric networkMetric;
    private UptimeMetric uptimeMetric;
}
