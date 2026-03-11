package com.example.HomeServerAPI.controller;

 
import com.example.HomeServerAPI.model.SystemLogDto;

public interface IMetricsController {

	public SystemLogDto prepareAndCreateMetrics();
}
