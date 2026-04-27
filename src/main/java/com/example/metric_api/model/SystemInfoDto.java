package com.example.metric_api.model;

import lombok.Data;

@Data
public class SystemInfoDto {

	private UptimeMetricDto uptime;
	private String hostname;
	private OsDto os;
	private CpuInfoDto cpuInfo;
	private DiskInfoDto diskInfo;
	private MemoryInfoDto memoryInfo;
	private MotherBoardDto motherBoard;
	private BiosDto bios;
}
