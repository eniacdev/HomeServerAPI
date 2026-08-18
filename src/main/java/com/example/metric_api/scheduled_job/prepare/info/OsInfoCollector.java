package com.example.metric_api.scheduled_job.prepare.info;

import java.lang.management.ManagementFactory;

import com.example.metric_api.check.MetricsValidator;
import com.example.metric_api.entitiy.Metrics;
import org.springframework.stereotype.Component;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.OsInfo;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

@Component
public class OsInfoCollector {
	
	public OsInfo collectOsMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		OsInfo osDto = new OsInfo();
		
		osDto.setOsName(MetricsValidator.validate(
				osBean.getName(),
				OsInfoCollector.class,
				"osName"
		));
		osDto.setOsVersion(MetricsValidator.validate(
				osBean.getVersion(),
				OsInfoCollector.class,
				"osVersion"
		));

		return osDto;
	}

}
