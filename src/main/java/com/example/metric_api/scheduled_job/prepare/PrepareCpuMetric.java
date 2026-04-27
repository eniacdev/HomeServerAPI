package com.example.metric_api.scheduled_job.prepare;

import java.lang.management.ManagementFactory;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

@Getter
@Component
public class PrepareCpuMetric {
	public CpuDto collectCpuMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		CpuDto cpuDto = new CpuDto();

		//işlemcinin diğer değerlerini almak için (cpuTemp, fan ve voltage).
		SystemInfo si = new SystemInfo();
		HardwareAbstractionLayer hal = si.getHardware();
		Sensors sensors = hal.getSensors();
		
		//cpuDto.setCpuCores(osBean.getAvailableProcessors());
		cpuDto.setProcessCpuLoad(osBean.getProcessCpuLoad() * 100);
		cpuDto.setSystemCpuLoad(osBean.getSystemCpuLoad() * 100);
		cpuDto.setSystemAverageLoad(osBean.getSystemLoadAverage());

		cpuDto.setCpuTemp(sensors.getCpuTemperature()); // Celsius
		cpuDto.setFanSpeeds(sensors.getFanSpeeds());  // RPM
		cpuDto.setCpuVolt(sensors.getCpuVoltage());
		
		if(/*cpuDto.getCpuCores() == null &&*/ cpuDto.getProcessCpuLoad() == null &&
		   cpuDto.getSystemAverageLoad() == null && cpuDto.getSystemCpuLoad() == null) {
			throw new BaseException(ResponseType.CPU_METRICS_NOT_COLLECTED);
		}
		return cpuDto;
	}
}
