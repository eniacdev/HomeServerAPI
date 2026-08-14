package com.example.metric_api.scheduled_job.prepare.metrics;

import com.example.metric_api.model.SystemMetrics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.scheduled_job.prepare.info.UptimeInfoCollector;

@Service
public class SystemMetricsCollector {

	private static final Logger log = LoggerFactory.getLogger(SystemMetricsCollector.class);
	// osBean gerekmeyebilir ama uzun vaadede ihtiyaç olacak.
	// OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemMetrics prepareSystemMetrics() throws Exception{
		
		SystemMetrics systemMetrics = new SystemMetrics();
		
		//Preparing Metrics
		CpuMetricCollector cpuMetric = new CpuMetricCollector();
		DiskMetricCollector diskMetric = new DiskMetricCollector();
		MemoryMetricCollector memoryMetric = new MemoryMetricCollector();
		UptimeInfoCollector uptimeMetric = new UptimeInfoCollector();
		NetworkMetricCollector networkMetric = new NetworkMetricCollector();
		
		log.info("the metrics is being preparing");

		systemMetrics.setCpu(cpuMetric.collectCpuMetrics());
		systemMetrics.setMemory(memoryMetric.collectMemoryMetrics());
		systemMetrics.setDisk(diskMetric.collectDiskMetrics());
		systemMetrics.setNetworkMetric(networkMetric.collectNetworkMetric());
		systemMetrics.setOsUptime(uptimeMetric.osUptime());
		systemMetrics.setServiceUptime(uptimeMetric.serviceUptime());
		
		return systemMetrics;
	}
}
