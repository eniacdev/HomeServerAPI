package com.example.metric_api.controller;

import com.example.metric_api.dto.*;
import com.example.metric_api.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.metric_api.response.ApiResponse;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.service.IMetricsService;

@RestController
@RequestMapping("/api/v1/metrics")
public class MetricsControllerImpl implements IMetricsController{
	
	private final IMetricsService metricsService;

	public MetricsControllerImpl(IMetricsService metricsService) {
		this.metricsService = metricsService;
	}

	// client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@PostMapping("/save")
	public ResponseEntity<ApiResponse<SystemMetricsDto>> saveAndGetMetrics() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.saveMetrics());
	}

	// tüm metrikleri toplar ancak veritabanına kaydetmez. sadece anlık alınır.
	@Override
	@GetMapping("/")
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getMetrics() throws Exception {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMetrics());
	}
 
	// sadece belirli metrikler ...
	@Override
	@GetMapping("/cpu")
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getCpuMetric());
	}

	@Override
	@GetMapping("/memory")
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMemoryMetric());
	}

	@Override
	@GetMapping("/disk")
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getDiskMetric());
	}

	// sadece sistemin bilgilerini toplar.
	@Override
	@GetMapping("/system")
	public ResponseEntity<ApiResponse<SystemInfoDto>> getSystemInfo() throws Exception{
		return ApiResponse.ok(ResponseType.SYSTEM_INFO_COLLECTED, metricsService.getSystemInfo());
	}

	@Override
	@DeleteMapping("/log/{id}")
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(@PathVariable(name = "id") long id) {
		return ApiResponse.ok(ResponseType.METRICS_DELETED, metricsService.deleteLogById(id));
	}

	@Override
	@GetMapping("/log/{id}")
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getLogById(@PathVariable(name = "id") long id) {
		return ApiResponse.ok(ResponseType.METRICS_FOUND, metricsService.getLogById(id));
	}

	@Override
	@GetMapping("/snapshot")
	public ResponseEntity<ApiResponse<MetricsSnapshot>> prepareSnapshot() {
		return ApiResponse.ok(ResponseType.BUILD_SNAPSHOT_SUCCESSFULLY, metricsService.buildSnapshot());
	}

	//sadece network bilgileri
	@Override
	@GetMapping("/network")
	public ResponseEntity<ApiResponse<NetworkMetricDto>> getNetworkMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getNetworkMetric());
	}
}