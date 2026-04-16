package com.example.metric_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuDto;
import com.example.metric_api.model.DiskDto;
import com.example.metric_api.model.MemoryDto;
import com.example.metric_api.model.SystemInfoDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.response.ResponseType;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.PrepareCpuMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareDiskMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareMemoryMetric;
import com.example.metric_api.scheduled_job.prepare.PrepareSystemInfo;
import com.example.metric_api.scheduled_job.prepare.PrepareSystemMetrics;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements IMetricsService{

	//kod bu şekilde refactor edildi. test yazmak için uygun ve daha az karmaşa (tam olarak değil).
	//genel olarak component anatasyonu önemli (sanırsam test yazmak için).
	private final PrepareSystemMetrics systemMetrics;
	private final PrepareJsonFile prepareJsonFile;
	private final PrepareCpuMetric cpuMetric;
	private final PrepareMemoryMetric memoryMetric;
	private final PrepareDiskMetric diskMetric;
	private final PrepareSystemInfo systemInfo;
	private static final Logger log = LoggerFactory.getLogger(PrepareSystemMetrics.class);

	
	@Override
	public SystemMetricsDto prepareAndSaveMetrics(){
		
		try {
		// schedule tetiklendiğinde servise (buraya) yönlendirir.
		//ayrıca client'ta manuel tetiklemeyi bu method ile gerçekleştirir.
		// bu method metrikleri toplar ve client tarafa gönderirken aynı zaman da metrikleri json dosyası olarak kaydeder.
			
		SystemMetricsDto createdMetrics = systemMetrics.prepareSystemMetrics();
	    
	    prepareJsonFile.writeJsonFile(createdMetrics);
	    
	    return createdMetrics;
	    
		}catch (Exception e) {
			log.error("Metrikler toplanırken bir hata oluştu: {}", e.getMessage());
	        throw new BaseException(ResponseType.METRICS_NOT_COLLECTED);
		}
	}

	@Override
	public SystemInfoDto prepareAndGetSystemInfo() throws Exception{
		return systemInfo.collectSystemInfo();
	}

	//bu method metrikleri json dosyası olarak kaydetmeden sadece metrikleri alınmasını sağlar. - veriler kaydedilmez -
	@Override
	public SystemMetricsDto getAllMetrics() throws Exception{
		return systemMetrics.prepareSystemMetrics();
	}

	@Override
	public CpuDto getCpuMetric() {
		return cpuMetric.collectCpuMetrics();
	}

	@Override
	public MemoryDto getMemoryMetric() {
		return memoryMetric.collectMemoryMetrics();
	}
	
	@Override
	public DiskDto getDiskMetric() {
		return diskMetric.collectDiskMetrics();
	}
}
