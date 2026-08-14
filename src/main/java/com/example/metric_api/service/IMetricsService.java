package com.example.metric_api.service;
import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.*;

public interface IMetricsService {

	public SystemMetricsDto saveAndGetMetrics();
	public SystemInfo getSystemInfo() throws Exception;
	public NetworkMetric getNetworkMetric();
	public SystemMetricsDto getMetrics() throws Exception;
	public CpuMetric getCpuMetric();
	public MemoryMetric getMemoryMetric();
	public DiskMetric getDiskMetric();
	public Boolean deleteLogById(long id);
	public SystemMetricsDto getLogById(long id);
	
}
