package com.example.metric_api.service;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;

public interface IMetricsService {

	public SystemMetricsDto prepareAndSaveMetrics();
	public SystemMetricsDto getAllMetrics() throws Exception;
	public SystemInfoDto prepareAndGetSystemInfo() throws Exception;
	public CpuDto getCpuMetric(); 
	public MemoryDto getMemoryMetric();
	public DiskDto getDiskMetric();
	
}
