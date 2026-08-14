package com.example.metric_api.scheduled_job.prepare.info;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.stereotype.Component;
import com.example.metric_api.model.UptimeMetric;

@Component
public class UptimeInfoCollector {
	
	public UptimeMetric collectUptimeMetric() throws Exception{
		
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		long uptime = rb.getUptime();
		
		UptimeMetric upTimeMetricDto = new UptimeMetric();
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
