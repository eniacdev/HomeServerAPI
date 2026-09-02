package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemMetricsLogDto {
	private Long logId;
	private LocalDateTime createdAt;
	private CpuMetricDto cpu;
	private MemoryMetricDto memory;
	private DiskMetricDto disk;
	private NetworkMetricDto network;
	private UptimeMetricDto uptime;
}
