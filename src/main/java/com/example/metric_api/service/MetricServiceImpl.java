package com.example.metric_api.service;

import com.example.metric_api.dto.*;
import com.example.metric_api.formatter.MetricFormatter;
import com.example.metric_api.mapper.*;
import com.example.metric_api.model.*;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.repository.IMetricsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.prepare.metrics.CpuMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.DiskMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.MemoryMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.NetworkMetricCollector;
import com.example.metric_api.scheduled_job.prepare.info.SystemInfoCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.SystemMetricsCollector;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{

	//kod bu şekilde refactor edildi. test yazmak için uygun ve daha az karmaşa (tam olarak değil).
	//genel olarak component anatasyonu önemli (sanırsam test yazmak için).

	private final MetricsMapper metricsMapper;
	private final CpuMapper cpuMapper;
	private final MemoryMapper memoryMapper;
	private final DiskMapper diskMapper;
	private final NetworkMapper networkMapper;
	private final SystemInfoMapper systemInfoMapper;
	private final SystemMetricsCollector systemMetrics;
	private final CpuMetricCollector cpuMetric;
	private final MemoryMetricCollector memoryMetric;
	private final DiskMetricCollector diskMetric;
	private final SystemInfoCollector systemInfo;
	private final IMetricsRepository metricsRepository;
	private final NetworkMetricCollector networkMetric;
	private static final Logger log = LoggerFactory.getLogger(MetricServiceImpl.class);

	// schedule tetiklendiğinde servise yani prepareAndSaveMetrics metoduna yönlendirir.
	// ayrıca client manuel tetiklemeyi bu method ile gerçekleştirir.
	// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri database'de kaydeder.
	
	@Override
	public SystemMetricsDto saveMetrics(){
		try {

			SystemMetrics collectedMetrics = systemMetrics.prepareSystemMetrics();
			Metrics metrics = metricsMapper.toEntity(collectedMetrics);
			Metrics savedMetrics = metricsRepository.save(metrics);

			log.info("metrics is prepared and saved.");

			SystemMetricsDto dto = metricsMapper.toDto(savedMetrics);

			return dto;

		}catch (Exception e) {
			log.error("Something went wrong: ", e);
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	// getAllMetrics metodu metrikleri veritabanına kaydetmeden sadece anlık metrikleri alınmasını sağlar. - veriler kaydedilmez -
	@Override
	public SystemMetricsDto getMetrics() {
		try {
			SystemMetrics collectedSystemMetrics = systemMetrics.prepareSystemMetrics();

			return metricsMapper.toDto(collectedSystemMetrics);

		} catch (Exception e) {
			log.error("Something went wrong while collecting metrics.", e);
			throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
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
	public SystemMetricsDto getLogById(long id) {
		Metrics metric = metricsRepository.findById(id)
				.orElseThrow(() -> new BaseException(ResponseType.METRICS_NOT_FOUND));

		return metricsMapper.toDto(metric);
	}

	@Override
	public CpuMetricDto getCpuMetric() {
		CpuMetric metric = cpuMetric.collectCpuMetrics();
		return cpuMapper.toDto(metric);
	}

	@Override
	public MemoryMetricDto getMemoryMetric() {
		MemoryMetric metric = memoryMetric.collectMemoryMetrics();
		return memoryMapper.toDto(metric);
	}

	@Override
	public DiskMetricDto getDiskMetric() {
		DiskMetric metric = diskMetric.collectDiskMetrics();
		return diskMapper.toDto(metric);
	}

	@Override
	public SystemInfoDto getSystemInfo() throws Exception{
		SystemInfo metric = systemInfo.collectSystemInfo();
		return systemInfoMapper.toDto(metric);
	}

	@Override
	public NetworkMetricDto getNetworkMetric(){
		NetworkMetric metric = networkMetric.collectNetworkMetric();
		return networkMapper.toDto(metric);
	}
}
