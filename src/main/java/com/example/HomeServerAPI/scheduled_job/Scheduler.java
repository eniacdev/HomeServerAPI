package com.example.HomeServerAPI.scheduled_job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.HomeServerAPI.service.IMetricsService;

@Component
public class Scheduler {
	
	@Autowired
	private IMetricsService metricsService;
	
	@Scheduled(cron = "0 45 10 * * *", zone = "Europe/Istanbul")
	public void doSchedulerJob() {
		try {
		System.out.println("schedule çalıştı");
		metricsService.prepareAndCreateMetrics();
		}catch (Exception e) {
			e.getMessage();
		}
	}
}
