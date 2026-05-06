package com.example.metric_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.metric_api.model.CpuMetricDto;
import com.example.metric_api.model.DiskMetricDto;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.response.ApiResponse;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.service.IMetricsService;

@RestController
@RequestMapping(path = "/homeserver/metrics")
public class MetricsControllerImpl implements IMetricsController{
	
	private final IMetricsService metricsService;
	
	//constructor injection.
	public MetricsControllerImpl(IMetricsService metricsService) {
		this.metricsService = metricsService;
	}

	// client schedule tetiklenmesini beklemek yerine kendi manuel olarak tetikleyebilir.
	@Override
	@PostMapping(path = "/collect")
	public ResponseEntity<ApiResponse<SystemMetricsDto>> prepareAndSaveMetrics() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.prepareAndSaveMetrics());
	}

	// tüm metrikleri toplar ancak JSON dosyası oluşturmaz ve DB'e kaydetmez. sadece anlık alınır.
	@Override
	@GetMapping(path = "/")
	public ResponseEntity<ApiResponse<SystemMetricsDto>> getAllMetrics() throws Exception {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getAllMetrics());
	}

 
	// sadece belirli metrikler ...
	@Override
	@GetMapping(path = "/cpu")
	public ResponseEntity<ApiResponse<CpuMetricDto>> getCpuMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getCpuMetric());
	}


	@Override
	@GetMapping(path = "/memory")
	public ResponseEntity<ApiResponse<MemoryMetricDto>> getMemoryMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getMemoryMetric());
	}

	@Override
	@GetMapping(path = "/disk")
	public ResponseEntity<ApiResponse<DiskMetricDto>> getDiskMetric() {
		return ApiResponse.ok(ResponseType.METRICS_COLLECTED, metricsService.getDiskMetric());
	}

	// sadece sistemin bilgilerini toplar.
	@Override
	@GetMapping(path = "/system")
	public ResponseEntity<ApiResponse<SystemInfoDto>> prepareAndGetSystemInfo() throws Exception{
		return ApiResponse.ok(ResponseType.SYSTEM_INFO_COLLECTED, metricsService.prepareAndGetSystemInfo());
	}


	@Override
	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<ApiResponse<Boolean>> deleteLogById(@PathVariable(name = "id") long id) {
		return ApiResponse.ok(ResponseType.METRICS_DELETED, metricsService.deleteLogById(id));
	}

}