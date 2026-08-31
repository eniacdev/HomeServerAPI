package com.example.metric_api.service;
import com.example.metric_api.dto.*;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.*;

public interface IMetricsService {

	public SystemMetricsDto saveMetrics();
	public SystemInfoDto getSystemInfo() throws Exception;
	public NetworkMetricDto getNetworkMetric();
	public SystemMetricsDto getMetrics() throws Exception;
	public CpuMetricDto getCpuMetric();
	public MemoryMetricDto getMemoryMetric();
	public DiskMetricDto getDiskMetric();
	public Boolean deleteLogById(long id);
	public SystemMetricsDto getLogById(long id);
	public MetricsSnapshot buildSnapshot();
}
