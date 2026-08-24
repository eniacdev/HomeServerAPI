package com.example.metric_api.controller;

 
import com.example.metric_api.dto.*;
import com.example.metric_api.model.*;
import org.springframework.http.ResponseEntity;

import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsDto>> saveAndGetMetrics();
	public ResponseEntity<ApiResponse<SystemInfoDto>> getSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<NetworkMetricDto>> getNetworkMetric();
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getMetrics() throws Exception;
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric();
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(long id);
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getLogById(long id);
}
