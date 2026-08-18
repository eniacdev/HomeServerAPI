package com.example.metric_api.scheduled_job.prepare.metrics;

import java.io.File;

import com.example.metric_api.check.MetricsValidator;
import com.example.metric_api.model.DiskMetric;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import org.springframework.stereotype.Component;

@Component
public class DiskMetricCollector {
	
	public DiskMetric collectDiskMetrics() {
		DiskMetric diskDto = new DiskMetric();
		File root = new File("/");
		
		diskDto.setFreeDisk(MetricsValidator.validate(
				root.getFreeSpace(),
				DiskMetricCollector.class,
				"freeDisk"
		));
		diskDto.setTotalDisk(MetricsValidator.validate(
				root.getTotalSpace(),
				DiskMetricCollector.class,
				"totalDisk"
		));
		diskDto.setDiskUsage(MetricsValidator.validate(
				diskDto.getTotalDisk() - diskDto.getFreeDisk(),
				DiskMetricCollector.class,
				"diskUsage"
		));
		
		return diskDto;
	}
}
