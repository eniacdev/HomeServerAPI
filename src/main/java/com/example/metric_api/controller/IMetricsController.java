package com.example.metric_api.controller;

 
import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.model.*;
import org.springframework.http.ResponseEntity;

import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsDto>> saveAndGetMetrics();
	public ResponseEntity<ApiResponse<SystemInfo>> getSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<NetworkMetric>> getNetworkMetric();
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getMetrics() throws Exception;
	public ResponseEntity<ApiResponse<CpuMetric>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryMetric>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskMetric>> getDiskMetric();
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(long id);
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getLogById(long id);
}
