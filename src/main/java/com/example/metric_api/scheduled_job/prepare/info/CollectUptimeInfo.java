package com.example.metric_api.scheduled_job.prepare.info;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import com.example.metric_api.model.UptimeMetricDto;

@Component
public class CollectUptimeInfo {
	
	public UptimeMetricDto collectUptimeMetric() throws Exception{
		
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		long uptime = rb.getUptime();
		
		UptimeMetricDto upTimeMetricDto = new UptimeMetricDto();
		upTimeMetricDto.setOsUptime(osUptime());
		upTimeMetricDto.setServiceUptime(uptime);
		
		return upTimeMetricDto;
	}
	
	public Long osUptime() throws Exception{
		
		String content = Files.readString(Path.of("/proc/uptime"));
	    return (long) Double.parseDouble(content.split(" ")[0]);
		
	}
	
	public Long serviceUptime() {

		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		Long serviceUpTime = rb.getUptime();
		
		return serviceUpTime;

	}

}
