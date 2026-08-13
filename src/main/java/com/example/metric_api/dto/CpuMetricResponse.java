package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuMetricResponse {
	
	private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Double cpuTemp;
	
}
