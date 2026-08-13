package com.example.metric_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfoResponse {

	private UptimeMetricResponse uptime;
	private String hostname;
	private OsInfoResponse os;
	private CpuInfoResponse cpuInfo;
	private Long totalMemory;
	private Long totalDisk;
	private MotherBoardInfoResponse motherBoard;
	private BiosInfoResponse bios;
	private NetworkInfoResponse networkInfo;
}
