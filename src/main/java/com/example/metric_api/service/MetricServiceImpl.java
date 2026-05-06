package com.example.metric_api.service;

import com.example.metric_api.mapper.MetricsMapper;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.repository.IMetricsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.Local;
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
import com.example.metric_api.scheduled_job.prepare.info.CollectUptimeInfo;
import com.example.metric_api.scheduled_job.prepare.metrics.CollectSystemMetrics;

import lombok.RequiredArgsConstructor;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Optional;

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
	private final CollectUptimeInfo uptimeInfo;
	//private final MetricsMapper metricsMapper;
	private static final Logger log = LoggerFactory.getLogger(CollectSystemMetrics.class);

	
	@Override
	public SystemMetricsDto prepareAndSaveMetrics(){
		
		try {

		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		// ayrıca client manuel tetiklemeyi bu method ile gerçekleştirir.
		// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri hem json dosyası hemde database'de kaydeder.
		
		SystemMetricsDto collectedMetrics = collectMetrics();

		saveMetrics(collectedMetrics);

		return collectedMetrics;

		}catch (Exception e) {
			log.error("Metrikler toplanırken bir hata oluştu: {}", e.getMessage());
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	// bu iki private metod uptime verilerini anlık alıyor, ancak arada büyük bir fark olabilior.
	// yakında refactor şart, ama şimdilik değil.
	private SystemMetricsDto collectMetrics() throws Exception{
		SystemMetricsDto collectedMetrics = systemMetrics.prepareSystemMetrics();
		collectedMetrics.setOsUptime(uptimeInfo.osUptime());
		collectedMetrics.setServiceUptime(uptimeInfo.serviceUptime());
		return collectedMetrics;
	}

	private void saveMetrics(SystemMetricsDto collectedMetrics) throws Exception{
		Metrics entityMetrics = MetricsMapper.toEntity(collectedMetrics);
		entityMetrics.setCreatedAt(LocalDateTime.now());
		entityMetrics.setOsUptime(uptimeInfo.osUptime());
		entityMetrics.setServiceUptime(uptimeInfo.serviceUptime());
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

	// bu method metrikleri json dosyası olarak kaydetmeden sadece metrikleri alınmasını sağlar. - veriler kaydedilmez -
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
