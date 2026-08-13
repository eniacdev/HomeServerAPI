package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryMetricDto {
	
	private Long memoryUsage;
	private Long freeMemory;
	private Long totalMemory;
}
