package com.example.metric_api.controller;

 
import com.example.metric_api.dto.*;
import org.springframework.http.ResponseEntity;

import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsResponse>> prepareAndSaveMetrics();
	public ResponseEntity<ApiResponse<SystemInfoResponse>> prepareAndGetSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<NetworkMetricResponse>> prepareAndGetNetworkMetric();
	public ResponseEntity<ApiResponse<SystemMetricsResponse>> getAllMetrics() throws Exception;
	public ResponseEntity<ApiResponse<CpuMetricResponse>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryMetricResponse>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskMetricResponse>> getDiskMetric();
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(long id);
	public ResponseEntity<ApiResponse<SystemMetricsResponse>> getLogById(long id);
}
