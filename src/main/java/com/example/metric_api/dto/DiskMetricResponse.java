package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskMetricResponse {
	
	private Long diskUsage;
	private Long freeDisk;
	private Long totalDisk;
}
