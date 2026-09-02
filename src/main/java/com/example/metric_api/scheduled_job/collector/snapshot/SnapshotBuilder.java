package com.example.metric_api.scheduled_job.collector.snapshot;

import com.example.metric_api.mapper.SnapshotMapper;
import com.example.metric_api.model.*;
import com.example.metric_api.scheduled_job.export.JsonFileBuilder;
import com.example.metric_api.scheduled_job.collector.info.*;
import com.example.metric_api.scheduled_job.collector.metrics.CpuMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.DiskMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.MemoryMetricCollector;
import com.example.metric_api.scheduled_job.collector.metrics.NetworkMetricCollector;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SnapshotBuilder {

    /*
        json dosyasınde hem anlık metrik hemde statik metrikleri eklemenin bilgilendirmesi açıdan
        daha iyi olabileceğini düşündüm.
    */

    private final SnapshotMapper snapshotMapper;

    private final CpuMetricCollector cpuMetricCollector;
    private final DiskMetricCollector diskMetricCollector;
    private final MemoryMetricCollector memoryMetricCollector;
    private final NetworkMetricCollector networkMetricCollector;

    private final CpuInfoCollector cpuInfoCollector;
    private final NetworkInfoCollector networkInfoCollector;
    private final OsInfoCollector osInfoCollector;
    private final BiosInfoCollector biosInfoCollector;
    private final MotherBoardInfoCollector motherBoardInfoCollector;

    private MetricsSnapshot metricsSnapshot;
    private StaticMetric staticMetric;
    private CurrentMetric currentMetric;
    private final JsonFileBuilder jsonFileBuilder;
    private static final Logger log = LoggerFactory.getLogger(SnapshotBuilder.class);

    public MetricsSnapshot buildSnapshot(){
        try {
            log.info("preparing snapshot...");

            //anlık metrikleri topla
            CpuMetric cpuMetric = cpuMetricCollector.collectCpuMetrics();
            MemoryMetric memoryMetric = memoryMetricCollector.collectMemoryMetrics();
            DiskMetric diskMetric = diskMetricCollector.collectDiskMetrics();
            NetworkMetric networkMetric = networkMetricCollector.collectNetworkMetric();

            //statik metrikleri topla
            CpuInfo cpuInfo = cpuInfoCollector.collectCpuInfo();
            NetworkInfo networkInfo = networkInfoCollector.collectNetworkInfo();
            OsInfo osInfo = osInfoCollector.collectOsMetrics();
            BiosInfo biosInfo = biosInfoCollector.collectBiosInfo();
            MotherBoardInfo motherBoardInfo = motherBoardInfoCollector.collectMotherBoardInfo();

            MetricsSnapshot snapshot = snapshotMapper.toSnapshot(
                    cpuMetric, cpuInfo, memoryMetric, diskMetric, networkMetric,
                    networkInfo, osInfo, biosInfo, motherBoardInfo, LocalDateTime.now()
            );

            log.info("snapshot is ready, writing a json file now.");

            // json dosyasını hazırla ve export et
            jsonFileBuilder.exportToJsonFile(snapshot);

            return snapshot;

        } catch (Exception e) {
            log.error("Something went wrong at preparing snapshot: {}", e );
           throw new RuntimeException(e);
        }
    }
}
