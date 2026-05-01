package com.example.metric_api.service;
import com.example.metric_api.model.CpuMetricDto;
import com.example.metric_api.model.DiskMetricDto;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;

public interface IMetricsService {

	public SystemMetricsDto prepareAndSaveMetrics();
	public SystemMetricsDto getAllMetrics() throws Exception;
	public SystemInfoDto prepareAndGetSystemInfo() throws Exception;
	public CpuMetricDto getCpuMetric(); 
	public MemoryMetricDto getMemoryMetric();
	public DiskMetricDto getDiskMetric();
	
}
