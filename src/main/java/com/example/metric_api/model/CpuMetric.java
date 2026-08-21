package com.example.metric_api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CpuMetric {
	
	private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Integer cpuTemp;
}
