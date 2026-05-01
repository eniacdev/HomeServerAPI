package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuMetricDto {
	
	private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Double cpuTemp;
	private Double cpuVolt;
	private int[] fanSpeeds;




}
