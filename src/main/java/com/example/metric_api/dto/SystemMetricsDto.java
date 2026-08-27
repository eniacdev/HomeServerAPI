package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsDto {

	private CpuMetricDto cpu;
	private MemoryMetricDto memory;
	private DiskMetricDto disk;
	private NetworkMetricDto network;
	private UptimeMetricDto uptime;
}
