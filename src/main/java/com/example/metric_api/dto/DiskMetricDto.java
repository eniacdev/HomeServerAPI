package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiskMetricDto {
	
	private Long diskUsage;
	private String diskUsageFormatted;
	private Long freeDisk;
	private String freeDiskFormatted;
	private Long totalDisk;
	private String totalDiskFormatted;
}
