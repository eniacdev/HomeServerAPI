package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.dto.CpuMetricResponse;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

import lombok.Getter;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

@Getter
@Component
public class CollectCpuMetric {
	public CpuMetricResponse collectCpuMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		CpuMetricResponse cpuDto = new CpuMetricResponse();

		//işlemcinin diğer değerlerini almak için (cpuTemp vb.)
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();
		
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());

		cpuDto.setCpuTemp(sensors.getCpuTemperature()); // Celsius
		
		if(cpuDto.getProcessCpuLoad() == null &&
		   cpuDto.getSystemAverageLoad() == null && cpuDto.getSystemCpuLoad() == null) {
			throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}

		return cpuDto;
	}
}
