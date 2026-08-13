package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsResponse {

	private CpuMetricResponse cpu;
	private MemoryMetricResponse memory;
	private DiskMetricResponse disk;
	private NetworkMetricResponse networkMetric;
	private Long serviceUptime;
	private Long osUptime;
}
