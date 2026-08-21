package com.example.metric_api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class MemoryMetric {
	
	private Long memoryUsage;
	private Long freeMemory;
	private Long totalMemory;
}
