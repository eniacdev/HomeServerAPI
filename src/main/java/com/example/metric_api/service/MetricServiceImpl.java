package com.example.metric_api.service;

import com.example.metric_api.mapper.MetricsMapper;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.repository.IMetricsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuMetricDto;
import com.example.metric_api.model.DiskMetricDto;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectCpuMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectDiskMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectMemoryMetric;
import com.example.metric_api.scheduled_job.prepare.info.CollectSystemInfo;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectSystemMetrics;

import lombok.RequiredArgsConstructor;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{

	//kod bu şekilde refactor edildi. test yazmak için uygun ve daha az karmaşa (tam olarak değil).
	//genel olarak component anatasyonu önemli (sanırsam test yazmak için).
	private final CollectSystemMetrics systemMetrics;
	private final PrepareJsonFile prepareJsonFile;
	private final CollectCpuMetric cpuMetric;
	private final CollectMemoryMetric memoryMetric;
	private final CollectDiskMetric diskMetric;
	private final CollectSystemInfo systemInfo;
	private final IMetricsRepository metricsRepository;
	//private final MetricsMapper metricsMapper;
	private static final Logger log = LoggerFactory.getLogger(CollectSystemMetrics.class);

	
	@Override
	public SystemMetricsDto prepareAndSaveMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetiklemeyi bu method ile gerçekleştirir.
		// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri json dosyası olarak kaydeder.
			
		SystemMetricsDto createdMetrics = systemMetrics.prepareSystemMetrics();
	    
	    prepareJsonFile.writeJsonFile(createdMetrics);
		Metrics DBmetrics = MetricsMapper.toEntity(createdMetrics);

		//system
		DBmetrics.setCreatedAt(LocalDateTime.now());
		//DBmetrics.setHostname(InetAddress.getLocalHost().getHostName());

		metricsRepository.save(DBmetrics);
	    
	    return createdMetrics;
	    
		}catch (Exception e) {
			log.error("Metrikler toplanırken bir hata oluştu: {}", e.getMessage());
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	//bu method metrikleri json dosyası olarak kaydetmeden sadece metrikleri alınmasını sağlar. - veriler kaydedilmez -
	@Override
	public SystemMetricsDto getAllMetrics() throws Exception{
		return systemMetrics.prepareSystemMetrics();
	}

	@Override
	public CpuMetricDto getCpuMetric() {
		return cpuMetric.collectCpuMetrics();
	}

	@Override
	public MemoryMetricDto getMemoryMetric() {
		return memoryMetric.collectMemoryMetrics();
	}
	
	@Override
	public DiskMetricDto getDiskMetric() {
		return diskMetric.collectDiskMetrics();
	}

	@Override
	public SystemInfoDto prepareAndGetSystemInfo() throws Exception{
		return systemInfo.collectSystemInfo();
	}
}
