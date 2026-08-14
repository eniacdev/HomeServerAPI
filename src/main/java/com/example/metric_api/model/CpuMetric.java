package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuMetric {
	
	private Double processCpuLoad;
	private String processCpuLoadFormatted;
	private Double systemCpuLoad;
	private String systemCpuLoadFormatted;
	private Double systemAverageLoad;
	private Double cpuTemp;
}
