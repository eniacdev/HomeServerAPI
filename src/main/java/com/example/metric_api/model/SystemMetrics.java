package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetrics {

	private CpuMetric cpu;
	private MemoryMetric memory;
	private DiskMetric disk;
	private NetworkMetric networkMetric;
	private Long serviceUptime;
	private Long osUptime;
}
