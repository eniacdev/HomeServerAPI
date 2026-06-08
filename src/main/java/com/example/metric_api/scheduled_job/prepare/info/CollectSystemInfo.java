package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectDiskMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectMemoryMetric;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollectSystemInfo {

	private final CollectOsInfo osInfo;
	private final CollectUptimeInfo uptimeInfo;
	private final CollectHostnameInfo hostnameInfo;
	private final CollectBiosInfo biosInfo;
	
	private final CollectMotherBoardInfo motherBoardInfo;
	private final CollectCpuInfo cpuInfo;
	private final CollectMemoryMetric memory;
	private final CollectDiskMetric disk;

	private final CollectNetworkInfo networkInfo;
		
	
	public SystemInfoDto collectSystemInfo() throws Exception{
		
		SystemInfoDto systemInfo = new SystemInfoDto();
		
		//system info
		systemInfo.setUptime(uptimeInfo.collectUptimeMetric());
		systemInfo.setHostname(hostnameInfo.getHostname());
		systemInfo.setOs(osInfo.collectOsMetrics());
		systemInfo.setBios(biosInfo.collectBiosInfo());

		//hardware info
		systemInfo.setMotherBoard(motherBoardInfo.collectMotherBoardInfo());
		systemInfo.setCpuInfo(cpuInfo.collectCpuInfo());
		systemInfo.setTotalMemory(memory.collectMemoryMetrics().getTotalMemory());
		systemInfo.setTotalDisk(disk.collectDiskMetrics().getTotalDisk());

		// netwok info
		systemInfo.setNetworkInfo(networkInfo.collectNetworkInfo());

		return systemInfo;
		
	}
}
