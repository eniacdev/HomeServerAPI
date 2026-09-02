package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsLog {
	private Long logId;
	private LocalDateTime createdAt;
	private CpuMetric cpuMetric;
	private MemoryMetric memoryMetric;
	private DiskMetric diskMetric;
	private NetworkMetric networkMetric;
	private UptimeMetric uptimeMetric;
}
