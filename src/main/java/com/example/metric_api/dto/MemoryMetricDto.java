package com.example.metric_api.dto;

import lombok.*;

@Getter
@Setter
@Builder
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
