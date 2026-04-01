package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpTimeMetricDto {

	private Long serviceUpTime;
	private Long OsUpTime;
	
}
