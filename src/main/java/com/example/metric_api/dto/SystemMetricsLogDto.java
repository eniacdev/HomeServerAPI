package com.example.metric_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
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
