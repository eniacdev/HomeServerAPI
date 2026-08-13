package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryMetricResponse {
	
	private Long memoryUsage;
	private Long freeMemory;
	private Long totalMemory;
}
