package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.model.SystemInfo;
import com.example.metric_api.scheduled_job.prepare.metrics.DiskMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.MemoryMetricCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemInfoCollector {

	private final OsInfoCollector osInfo;
	private final UptimeInfoCollector uptimeInfo;
	private final BiosInfoCollector biosInfo;
	private final MotherBoardInfoCollector motherBoardInfo;
	private final CpuInfoCollector cpuInfo;
	private final MemoryMetricCollector memory;
	private final DiskMetricCollector disk;
	private final NetworkInfoCollector networkInfo;
		
	
	public SystemInfo collectSystemInfo() throws Exception{
		
		SystemInfo systemInfo = new SystemInfo();
		
		//system info
		systemInfo.setUptime(uptimeInfo.collectUptimeMetric());
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
