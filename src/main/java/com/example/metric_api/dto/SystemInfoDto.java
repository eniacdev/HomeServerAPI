package com.example.metric_api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SystemInfoDto {

	private UptimeMetricDto uptime;
	private String hostname;
	private OsInfoDto os;
	private CpuInfoDto cpuInfo;
	private List<GpuInfoDto> gpuInfo;
	private Long totalMemory;
	private String totalMemoryFormatted;
	private Long totalDisk;
	private String totalDiskFormatted;
	private MotherBoardInfoDto motherBoard;
	private BiosInfoDto bios;
	private NetworkInfoDto network;
}
