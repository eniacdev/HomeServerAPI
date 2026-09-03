package com.example.metric_api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfo {

	private UptimeMetric uptime;
	private String hostname;
	private OsInfo os;
	private CpuInfo cpuInfo;
	private List<GpuInfo> gpuInfo;
	private Long totalMemory;
	private Long totalDisk;
	private MotherBoardInfo motherBoard;
	private BiosInfo bios;
	private NetworkInfo network;
}
