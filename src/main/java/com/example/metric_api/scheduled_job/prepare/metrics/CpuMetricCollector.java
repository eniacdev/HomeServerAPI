package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import com.example.metric_api.check.CheckInfoAndMetrics;
import com.example.metric_api.model.CpuMetric;
import com.sun.management.OperatingSystemMXBean;

import lombok.Getter;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

@Getter
@Component
public class CpuMetricCollector {
	public CpuMetric collectCpuMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		CpuMetric cpuDto = new CpuMetric();

		//işlemcinin diğer değerlerini almak için (cpuTemp vb.)
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();
		
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());

		cpuDto.setCpuTemp(sensors.getCpuTemperature()); // Celsius

		CheckInfoAndMetrics.checkCpuMetrics(cpuDto);

		return cpuDto;
	}
}
