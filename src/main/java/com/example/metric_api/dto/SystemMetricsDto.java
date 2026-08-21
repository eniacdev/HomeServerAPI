package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsDto {

	private CpuMetricDto cpuMetricDto;
	private MemoryMetricDto memoryMetricDto;
	private DiskMetricDto diskMetricDto;
	private NetworkMetricDto networkMetricDto;
	private Long serviceUptime;
	private Long osUptime;
}
