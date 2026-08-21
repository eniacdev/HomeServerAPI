package com.example.metric_api.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class DiskMetric {
	
	private Long diskUsage;
	private Long freeDisk;
	private Long totalDisk;
}
