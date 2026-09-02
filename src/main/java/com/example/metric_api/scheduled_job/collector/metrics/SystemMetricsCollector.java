package com.example.metric_api.scheduled_job.collector.metrics;

import com.example.metric_api.model.SystemMetricsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.scheduled_job.collector.info.UptimeInfoCollector;

@Service
public class SystemMetricsCollector {

	private static final Logger log = LoggerFactory.getLogger(SystemMetricsCollector.class);
	// osBean gerekmeyebilir ama uzun vaadede ihtiyaç olacak.
	// OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemMetricsLog prepareSystemMetrics() throws Exception{
		
		SystemMetricsLog systemMetrics = new SystemMetricsLog();
		
		//Preparing Metrics
		// statik yapmayı unutma 12.03.2026
		CpuMetricCollector cpuMetric = new CpuMetricCollector();
		DiskMetricCollector diskMetric = new DiskMetricCollector();
		MemoryMetricCollector memoryMetric = new MemoryMetricCollector();
		UptimeInfoCollector uptimeMetric = new UptimeInfoCollector();
		NetworkMetricCollector networkMetric = new NetworkMetricCollector();
		
		log.info("the metrics is being preparing");

		systemMetrics.setCpuMetric(cpuMetric.collectCpuMetrics());
		systemMetrics.setMemoryMetric(memoryMetric.collectMemoryMetrics());
		systemMetrics.setDiskMetric(diskMetric.collectDiskMetrics());
		systemMetrics.setNetworkMetric(networkMetric.collectNetworkMetric());
		systemMetrics.setUptimeMetric(uptimeMetric.collectUptimeMetric()); // bu satır değişti sadece
		
		return systemMetrics;
	}
}
