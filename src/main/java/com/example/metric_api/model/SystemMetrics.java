package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetrics {

	private CpuMetric cpuMetric;
	private MemoryMetric memoryMetric;
	private DiskMetric diskMetric;
	private NetworkMetric networkMetric;
	private Long serviceUptime;
	private Long osUptime;
}
