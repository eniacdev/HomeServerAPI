package com.example.metric_api.service;

import com.example.metric_api.dto.*;
import com.example.metric_api.mapper.*;
import com.example.metric_api.model.*;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.repository.IMetricsRepository;
import com.example.metric_api.scheduled_job.collector.snapshot.SnapshotBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.collector.metrics.CpuMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.DiskMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.MemoryMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.NetworkMetricCollector;
import com.example.metric_api.scheduled_job.collector.info.SystemInfoCollector;
import com.example.metric_api.scheduled_job.collector.metrics.SystemMetricsCollector;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
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
	private final NetworkMetricCollector networkMetric;

	private final IMetricsRepository metricsRepository;
	private final SnapshotBuilder snapshotBuilder;
	private static final Logger log = LoggerFactory.getLogger(MetricServiceImpl.class);

	// schedule tetiklendiğinde servise yani prepareAndSaveMetrics metoduna yönlendirir.
	// ayrıca client manuel tetiklemeyi bu method ile gerçekleştirir.
	// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri database'de kaydeder.
	
	@Override
	public SystemMetricsLogDto saveMetrics(){
		try {
			SystemMetricsLog collectedMetrics = systemMetrics.prepareSystemMetrics();
			Metrics metrics = metricsMapper.toEntity(collectedMetrics);
			Metrics savedMetrics = metricsRepository.save(metrics);

			log.info("metrics is prepared and saved.");

			SystemMetricsLogDto dto = metricsMapper.toDtoLog(savedMetrics);

			return dto;

		}catch (Exception e) {
			log.error("Something went wrong: ", e);
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	// getAllMetrics metodu metrikleri veritabanına kaydetmeden sadece anlık metrikleri alınmasını sağlar. - veriler kaydedilmez -
	@Override
	public SystemMetricsLogDto getMetrics() {
		try {
			SystemMetricsLog collectedSystemMetrics = systemMetrics.prepareSystemMetrics();
			Metrics metrics = metricsMapper.toEntity(collectedSystemMetrics);
			return metricsMapper.toDtoLog(metrics);

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
	public SystemMetricsLogDto getLogById(long id) {
		Metrics metric = metricsRepository.findById(id)
				.orElseThrow(() -> new BaseException(ResponseType.METRICS_NOT_FOUND));

		return metricsMapper.toDtoLog(metric);
	}

	@Override
	public Page<SystemMetricsLogDto> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Integer pageNumber, Integer pageSize, String sortedBy) {
		// nasıl sıralanacağını belirle
		Sort sort = Sort.unsorted();
		if (sortedBy.equalsIgnoreCase("asc")) {
			 sort = Sort.by("createdAt").ascending();
		} else {
			sort = Sort.by("createdAt").descending();
		}
		// sayfalanması için parametreler ver
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		// veritabanından sayfalar halinde getir
		Page<Metrics> page = metricsRepository.findByCreatedAtBetween(start, end, pageable);
		// response etmek için mapper ile dto'ya çevir
		List<SystemMetricsLogDto> responseList = metricsMapper.toDtoList(page.getContent());
		// PageImpl ile dto'ları sayfalanmış olarak döndür
		return new PageImpl<>(responseList, pageable, page.getTotalElements());
	}

	// anlık ve statik verileri json olarak kaydeder
	@Override
	public MetricsSnapshot buildSnapshot() {
		MetricsSnapshot snapshot = snapshotBuilder.buildSnapshot();
		return snapshot;
	}

	// sistem verileri
	@Override
	public SystemInfoDto getSystemInfo() throws Exception{
		SystemInfo metric = systemInfo.collectSystemInfo();
		return systemInfoMapper.toDto(metric);
	}

	// sadece belirli metrikler ...

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
	public NetworkMetricDto getNetworkMetric(){
		NetworkMetric metric = networkMetric.collectNetworkMetric();
		return networkMapper.toDto(metric);
	}
}
