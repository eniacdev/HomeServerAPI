package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsDto {

	private CpuMetricDto cpu;
	private MemoryMetricDto memory;
	private DiskMetricDto disk;
	private NetworkMetricDto networkMetric;
	private Long serviceUptime;
	private Long osUptime;
}
