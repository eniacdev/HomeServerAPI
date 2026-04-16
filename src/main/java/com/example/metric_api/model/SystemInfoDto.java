package com.example.metric_api.model;

import lombok.Data;

@Data
public class SystemInfoDto {

	private UptimeMetricDto uptime;
	private String hostname;
	private OsDto os;
}
