package com.example.metric_api.service;

import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.model.*;
import com.example.metric_api.mapper.MetricsMapper;
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

	private final MetricsMapper mapper;
	private final SystemMetricsCollector systemMetrics;
	private final CpuMetricCollector cpuMetric;
	private final MemoryMetricCollector memoryMetric;
	private final DiskMetricCollector diskMetric;
	private final SystemInfoCollector systemInfo;
	private final IMetricsRepository metricsRepository;
	private final NetworkMetricCollector networkMetric;
	private static final Logger log = LoggerFactory.getLogger(SystemMetricsCollector.class);

	// schedule tetiklendiğinde servise yani prepareAndSaveMetrics metoduna yönlendirir.
	// ayrıca client manuel tetiklemeyi bu method ile gerçekleştirir.
	// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri database'de kaydeder.
	
	@Override
	public SystemMetricsDto saveAndGetMetrics(){
		try {

			SystemMetrics collectedMetrics = systemMetrics.prepareSystemMetrics();
			Metrics metrics = mapper.toEntity(collectedMetrics);
			Metrics savedMetrics = metricsRepository.save(metrics);

			log.info("metrics is prepared and saved.");

			return mapper.toDto(savedMetrics);

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

			return mapper.toDto(collectedSystemMetrics);
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
		Optional<Metrics> optional = metricsRepository.findById(id);

		if(optional.isEmpty()){
			throw new BaseException(ResponseType.METRICS_NOT_FOUND);
		}

		return mapper.toDto(optional.get());
	}

	@Override
	public CpuMetric getCpuMetric() {
		return cpuMetric.collectCpuMetrics();
	}

	@Override
	public MemoryMetric getMemoryMetric() {
		return memoryMetric.collectMemoryMetrics();
	}

	@Override
	public DiskMetric getDiskMetric() {
		return diskMetric.collectDiskMetrics();
	}

	@Override
	public SystemInfo getSystemInfo() throws Exception{
		return systemInfo.collectSystemInfo();
	}

	@Override
	public NetworkMetric getNetworkMetric(){
		return networkMetric.collectNetworkMetric();
	}
}
