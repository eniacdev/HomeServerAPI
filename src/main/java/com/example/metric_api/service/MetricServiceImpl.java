package com.example.metric_api.service;

import com.example.metric_api.dto.*;
import com.example.metric_api.mapper.MetricsMapper;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.repository.IMetricsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectCpuMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectDiskMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectMemoryMetric;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectNetworkMetric;
import com.example.metric_api.scheduled_job.prepare.info.CollectSystemInfo;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectSystemMetrics;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{

	//kod bu şekilde refactor edildi. test yazmak için uygun ve daha az karmaşa (tam olarak değil).
	//genel olarak component anatasyonu önemli (sanırsam test yazmak için).

	private final MetricsMapper mapper;
	private final CollectSystemMetrics systemMetrics;
	private final CollectCpuMetric cpuMetric;
	private final CollectMemoryMetric memoryMetric;
	private final CollectDiskMetric diskMetric;
	private final CollectSystemInfo systemInfo;
	private final IMetricsRepository metricsRepository;
	private final CollectNetworkMetric networkMetric;
	private static final Logger log = LoggerFactory.getLogger(CollectSystemMetrics.class);

	// schedule tetiklendiğinde servise yani prepareAndSaveMetrics metoduna yönlendirir.
	// ayrıca client manuel tetiklemeyi bu method ile gerçekleştirir.
	// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri database'de kaydeder.
	
	@Override
	public SystemMetricsResponse prepareAndSaveMetrics(){
		
		try {
		
		SystemMetricsResponse collectedMetrics = collectMetrics();

		saveMetrics(collectedMetrics);

		log.info("metrics is prepared and saved.");

		return collectedMetrics;

		}catch (Exception e) {
			log.error("Something went wrong: {}", e.getMessage());
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	// bu iki private metod uptime verisini taşırken hatalı veya farklı anlık uptime verileri olabiliyor.
	// sorun şimdilik çözüldü gibi ancak uzun vaadede geliştirme yapılırken bu dikkate alınmalı.

	private SystemMetricsResponse collectMetrics() throws Exception{
		SystemMetricsResponse collectedMetrics = systemMetrics.prepareSystemMetrics();
		return collectedMetrics;
	}

	private void saveMetrics(SystemMetricsResponse collectedMetrics) throws Exception{
		Metrics entityMetrics = mapper.toEntity(collectedMetrics);
		entityMetrics.setCreatedAt(LocalDateTime.now());
		metricsRepository.save(entityMetrics);
	}

	@Override
	public Boolean deleteLogById(long id) {
		Optional optional = metricsRepository.findById(id);

		if(optional.isEmpty()){
			throw new BaseException(ResponseType.METRICS_NOT_FOUND);
		}
		
		metricsRepository.deleteById(id);

		return true;
	}

	@Override
	public SystemMetricsResponse getLogById(long id) {
		Optional<Metrics> optional = metricsRepository.findById(id);

		if(optional.isEmpty()){
			throw new BaseException(ResponseType.METRICS_NOT_FOUND);
		}

		Metrics metrics = optional.get();

		return mapper.toDto(metrics);
	}

	// getAllMetrics metodu metrikleri veritabanına kaydetmeden sadece anlık metrikleri alınmasını sağlar. - veriler kaydedilmez -

	@Override
	public SystemMetricsResponse getAllMetrics() throws Exception{
		return systemMetrics.prepareSystemMetrics();
	}

	@Override
	public CpuMetricResponse getCpuMetric() {
		return cpuMetric.collectCpuMetrics();
	}

	@Override
	public MemoryMetricResponse getMemoryMetric() {
		return memoryMetric.collectMemoryMetrics();
	}
	
	@Override
	public DiskMetricResponse getDiskMetric() {
		return diskMetric.collectDiskMetrics();
	}

	@Override
	public SystemInfoResponse prepareAndGetSystemInfo() throws Exception{
		return systemInfo.collectSystemInfo();
	}

	@Override
	public NetworkMetricResponse prepareAndGetNetworkMetric(){
		return networkMetric.collectNetworkMetric();
	}
}
