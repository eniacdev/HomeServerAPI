package com.example.metric_api.scheduled_job.prepare.metrics;

import java.lang.management.ManagementFactory;

import com.example.metric_api.check.MetricsValidator;
import com.example.metric_api.entitiy.Metrics;
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
		//işlemcinin değerlerini almak için (cpuTemp vb.)
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		CpuMetric cpu = new CpuMetric();
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();

		cpu.setProcessCpuLoad(MetricsValidator.validate(
				osBean.getProcessCpuLoad() * 100,
				CpuMetricCollector.class,
				"processCpuLoad"
		));
		cpu.setSystemCpuLoad(MetricsValidator.validate(
				osBean.getSystemCpuLoad() * 100,
				CpuMetricCollector.class,
				"systemCpuLoad"
		));
		cpu.setSystemAverageLoad(MetricsValidator.validate(
				osBean.getSystemLoadAverage(),
				CpuMetricCollector.class,
				"systemLoadAverage"
		));
		// Celsius
		cpu.setCpuTemp(MetricsValidator.validate(
				sensors.getCpuTemperature(),
				CpuMetricCollector.class,
				"cpuTemperature"
		));

		return cpu;
	}
}
