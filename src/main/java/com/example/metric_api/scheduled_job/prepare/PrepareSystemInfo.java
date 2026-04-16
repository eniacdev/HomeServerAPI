package com.example.metric_api.scheduled_job.prepare;

import com.example.metric_api.model.SystemInfoDto;
import org.springframework.stereotype.Component;

@Component
public class PrepareSystemInfo {

	
	public SystemInfoDto collectSystemInfo() throws Exception{
		
		SystemInfoDto systemInfoDto = new SystemInfoDto();
		
		PrepareOsMetric osMetric = new PrepareOsMetric();
		PrepareUptimeMetric uptimeMetric = new PrepareUptimeMetric();
		PrepareHostnameMetric hostnameMetric = new PrepareHostnameMetric();
		
		systemInfoDto.setUptime(uptimeMetric.collectUptimeMetric());
		systemInfoDto.setHostname(hostnameMetric.getHostname());
		systemInfoDto.setOs(osMetric.collectOsMetrics());
		
		return systemInfoDto;
	}
}
