package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import com.example.metric_api.check.MetricsValidator;
import com.example.metric_api.model.MemoryMetric;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

@Component
public class MemoryMetricCollector {
	
	public MemoryMetric collectMemoryMetrics() {

		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		MemoryMetric memoryDto = new MemoryMetric();
		
		memoryDto.setFreeMemory(MetricsValidator.validate(
				osBean.getFreeMemorySize(),
				MemoryMetricCollector.class,
				"freeMemory"
		));
		memoryDto.setTotalMemory(MetricsValidator.validate(
				osBean.getTotalMemorySize(),
				MemoryMetricCollector.class,
				"totalMemory"
		));
		memoryDto.setMemoryUsage(MetricsValidator.validate(
				memoryDto.getTotalMemory() - memoryDto.getFreeMemory(),
				MemoryMetricCollector.class,
				"memoryUsage"
		));

		return memoryDto;
	}
}
