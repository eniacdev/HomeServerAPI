package com.example.metric_api.service;
import com.example.metric_api.dto.*;

public interface IMetricsService {

	public SystemMetricsResponse prepareAndSaveMetrics();
	public SystemInfoResponse prepareAndGetSystemInfo() throws Exception;
	public NetworkMetricResponse prepareAndGetNetworkMetric();
	public SystemMetricsResponse getAllMetrics() throws Exception;
	public CpuMetricResponse getCpuMetric();
	public MemoryMetricResponse getMemoryMetric();
	public DiskMetricResponse getDiskMetric();
	public Boolean deleteLogById(long id);
	public SystemMetricsResponse getLogById(long id);
	
}
