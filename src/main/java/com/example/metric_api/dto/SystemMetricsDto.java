package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsDto {
    private Long logId;
    private LocalDateTime createdAt;
    private CpuMetricDto cpu;
    private MemoryMetricDto memory;
    private DiskMetricDto disk;
    private NetworkMetricDto network;
    private UptimeMetricDto uptime;
}
