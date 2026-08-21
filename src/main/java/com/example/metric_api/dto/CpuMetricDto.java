package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuMetricDto {
	
	private Double processCpuLoad;
	private String processCpuLoadFormatted;
	private Double systemCpuLoad;
	private String systemCpuLoadFormatted;
	private Double systemAverageLoad;
	private String systemAverageLoadFormatted;
	private String cpuTemp;
	
}
