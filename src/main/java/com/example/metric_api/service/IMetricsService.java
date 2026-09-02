package com.example.metric_api.service;
import com.example.metric_api.dto.*;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.*;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface IMetricsService {

	public SystemMetricsLogDto saveMetrics();
	public SystemInfoDto getSystemInfo() throws Exception;
	public NetworkMetricDto getNetworkMetric();
	public SystemMetricsLogDto getMetrics() throws Exception;
	public CpuMetricDto getCpuMetric();
	public MemoryMetricDto getMemoryMetric();
	public DiskMetricDto getDiskMetric();
	public Boolean deleteLogById(long id);
	public SystemMetricsLogDto getLogById(long id);
	public MetricsSnapshot buildSnapshot();
	public Page<SystemMetricsLogDto> findByCreatedAtBetween(
			LocalDateTime start, LocalDateTime end, Integer pageNumber, Integer pageSize, String sortedBy);
}
