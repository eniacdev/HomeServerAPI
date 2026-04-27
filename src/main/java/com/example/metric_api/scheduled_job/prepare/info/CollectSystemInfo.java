package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.model.SystemInfoDto;
import org.springframework.stereotype.Component;

@Component
public class CollectSystemInfo {

	
	public SystemInfoDto collectSystemInfo() throws Exception{
		
		SystemInfoDto systemInfoDto = new SystemInfoDto();
		
		CollectOsInfo osMetric = new CollectOsInfo();
		CollectUptimeInfo uptimeMetric = new CollectUptimeInfo();
		CollectHostnameInfo hostnameMetric = new CollectHostnameInfo();
		
		systemInfoDto.setUptime(uptimeMetric.collectUptimeMetric());
		systemInfoDto.setHostname(hostnameMetric.getHostname());
		systemInfoDto.setOs(osMetric.collectOsMetrics());
		
		return systemInfoDto;
	}
}
