package com.example.metric_api.controller;

 
import org.springframework.http.ResponseEntity;

import com.example.metric_api.model.CpuMetricDto;
import com.example.metric_api.model.DiskMetricDto;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.response.ApiResponse;


public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemMetricsDto>> prepareAndSaveMetrics();
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getAllMetrics() throws Exception;
	public ResponseEntity<ApiResponse<SystemInfoDto>> prepareAndGetSystemInfo() throws Exception;
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric();
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric();
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric();
}
