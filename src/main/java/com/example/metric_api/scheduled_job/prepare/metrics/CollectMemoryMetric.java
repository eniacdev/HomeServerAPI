package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;
import org.springframework.stereotype.Component;

@Component
public class CollectMemoryMetric {
	
	public MemoryMetricDto collectMemoryMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		MemoryMetricDto memoryDto = new MemoryMetricDto();
		
		memoryDto.setFreeMemory(osBean.getFreeMemorySize());
		memoryDto.setTotalMemory(osBean.getTotalMemorySize());
		memoryDto.setMemoryUsage(memoryDto.getTotalMemory() - memoryDto.getFreeMemory());

		Long totalMemory = osBean.getTotalMemorySize();
		memoryDto.setMemoryUsage(totalMemory - memoryDto.getFreeMemory());
		
		if(memoryDto.getFreeMemory() == null && memoryDto.getMemoryUsage() == null &&
		   memoryDto.getTotalMemory() == null) {
			throw new BaseException(ResponseType.MEMORY_METRICS_NOT_COLLECTED);
		}
		
		return memoryDto;
	}

}
