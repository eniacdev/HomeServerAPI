package com.example.metric_api.dto;

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
	private String totalMemoryFormatted;
	private Long totalDisk;
	private String totalDiskFormatted;
	private MotherBoardInfoDto motherBoard;
	private BiosInfoDto bios;
	private NetworkInfoDto network;
}
