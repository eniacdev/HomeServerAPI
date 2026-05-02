package com.example.metric_api.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsDto {

	//private JsonFile jsonFile;
	private CpuMetricDto cpu;
	private MemoryMetricDto memory;
	private DiskMetricDto disk;
	private Long serviceUptime;
	private Long osUptime;
}
