package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfoDto {

	private UptimeMetricDto uptime;
	private String hostname;
	private OsInfoDto os;
	private CpuInfoDto cpuInfo;
	private Long totalMemory;
	private Long totalDisk;
	private MotherBoardInfoDto motherBoard;
	private BiosInfoDto bios;
}
