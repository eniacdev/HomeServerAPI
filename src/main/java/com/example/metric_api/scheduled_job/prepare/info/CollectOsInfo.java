package com.example.metric_api.scheduled_job.prepare.info;

import java.lang.management.ManagementFactory;

import org.springframework.stereotype.Component;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.OsInfoDto;
import com.example.metric_api.response.ResponseType;
import com.sun.management.OperatingSystemMXBean;

@Component
public class CollectOsInfo {
	
	public OsInfoDto collectOsMetrics() {
		OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		OsInfoDto osDto = new OsInfoDto();
		
		osDto.setOsName(osBean.getName());
		osDto.setOsVersion(osBean.getVersion());
		
		if(osDto.getOsName() == null && osDto.getOsVersion() == null) {
			throw new BaseException(ResponseType.OS_METRICS_NOT_COLLECTED);
		}
		return osDto;
	}

}
