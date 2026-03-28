package com.example.metric_api.controller;

 
import org.springframework.http.ResponseEntity;

import com.example.metric_api.model.SystemLogDto;
import com.example.metric_api.response.ApiResponse;

public interface IMetricsController {

	public ResponseEntity<ApiResponse<SystemLogDto>> prepareAndCreateMetrics();
}
