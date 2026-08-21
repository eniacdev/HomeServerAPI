package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryMetricDto {
	
	private Long memoryUsage;
	private String memoryUsageFormatted;
	private Long freeMemory;
	private String freeMemoryFormatted;
	private Long totalMemory;
	private String totalMemoryFormatted;
}
