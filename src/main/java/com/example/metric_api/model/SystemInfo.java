package com.example.metric_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfo {

	private UptimeMetric uptime;
	private String hostname;
	private OsInfo os;
	private CpuInfo cpuInfo;
	private Long totalMemory;
	private Long totalDisk;
	private MotherBoardInfo motherBoard;
	private BiosInfo bios;
	private NetworkInfo networkInfo;
}
