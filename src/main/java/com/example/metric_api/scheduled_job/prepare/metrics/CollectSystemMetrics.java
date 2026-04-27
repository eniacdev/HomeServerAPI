package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.metric_api.model.SystemMetricsDto;
import com.sun.management.OperatingSystemMXBean;

@Service
public class CollectSystemMetrics {

	// gerçekleştirilen işlemleri loglara yansıtmak için kullandım.
	// importlara dikkat.
	private static final Logger log = LoggerFactory.getLogger(CollectSystemMetrics.class);
	OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	
	public SystemMetricsDto prepareSystemMetrics() throws Exception{
		
		SystemMetricsDto metric = new SystemMetricsDto();
		
		//Preparing Metrics
		CollectCpuMetric cpuMetric = new CollectCpuMetric();
		CollectDiskMetric diskMetric = new CollectDiskMetric();
		CollectMemoryMetric memoryMetric = new CollectMemoryMetric();
		
		log.warn("the metrics is being preparing");
		
		metric.setCpu(cpuMetric.collectCpuMetrics());
		metric.setMemory(memoryMetric.collectMemoryMetrics());
		metric.setDisk(diskMetric.collectDiskMetrics());
		
		return metric;
	}
}
