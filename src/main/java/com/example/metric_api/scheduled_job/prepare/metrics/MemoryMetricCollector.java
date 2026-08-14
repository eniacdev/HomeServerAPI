package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

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
		
		memoryDto.setFreeMemory(osBean.getFreeMemorySize());
		memoryDto.setTotalMemory(osBean.getTotalMemorySize());
		memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());

		Long totalMemory = osBean.getTotalMemorySize();

		memoryDto.setMemoryUsage(totalMemory - memoryDto.getFreeMemory());
		
		if(memoryDto.getFreeMemory() == null && memoryDto.getMemoryUsage() == null &&
		   memoryDto.getTotalMemory() == null) {
			throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
		
		return memoryDto;
	}
}
