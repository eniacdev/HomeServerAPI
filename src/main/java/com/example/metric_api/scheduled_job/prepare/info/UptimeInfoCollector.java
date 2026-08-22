package com.example.metric_api.scheduled_job.prepare.info;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.Path;

import com.example.metric_api.check.MetricsValidator;
import org.springframework.stereotype.Component;
import com.example.metric_api.model.UptimeMetric;

@Component
public class UptimeInfoCollector {
	
	public UptimeMetric collectUptimeMetric() throws Exception{

		// RuntimeMXBean sayesinde sistemin ve servisin Uptime değerlerini alır
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		// long uptime = rb.getUptime();
		
		UptimeMetric upTimeMetric = new UptimeMetric();
		upTimeMetric.setOsUptime(MetricsValidator.validate(
				osUptime(),
				UptimeInfoCollector.class,
				"osUptime"
		));
		upTimeMetric.setServiceUptime(MetricsValidator.validate(
				serviceUptime(),
				UptimeInfoCollector.class,
				"serviceUptime"
		));
		
		return upTimeMetric;
	}
	
	public Long osUptime() throws Exception{

		// değerleri dosyadan okur
		String content = Files.readString(Path.of("/proc/uptime"));
	    return (long) Double.parseDouble(content.split(" ")[0]);
		
	}
	
	public Long serviceUptime() {

		// milisaniye cinsinden ham veri veriyor. 1000'e bölünmesi gerekiyor, saniye cinsi olması için.
		RuntimeMXBean rb = ManagementFactory.getRuntimeMXBean();
		long serviceUpTime = rb.getUptime() / 1000;
		
		return serviceUpTime;

	}

}
