package com.example.metric_api.mapper;

import com.example.metric_api.dto.CpuMetricResponse;
import com.example.metric_api.dto.DiskMetricResponse;
import com.example.metric_api.dto.MemoryMetricResponse;
import com.example.metric_api.dto.NetworkMetricResponse;
import com.example.metric_api.dto.SystemMetricsResponse;
import com.example.metric_api.entitiy.Metrics;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-13T11:05:18+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (JetBrains s.r.o.)"
)
@Component
public class MetricsMapperImpl implements MetricsMapper {

    @Override
    public Metrics toEntity(SystemMetricsResponse systemMetricsDto) {
        if ( systemMetricsDto == null ) {
            return null;
        }

        Metrics metrics = new Metrics();

        metrics.setProcessCpuLoad( systemMetricsDtoCpuProcessCpuLoad( systemMetricsDto ) );
        metrics.setSystemCpuLoad( systemMetricsDtoCpuSystemCpuLoad( systemMetricsDto ) );
        metrics.setSystemAverageLoad( systemMetricsDtoCpuSystemAverageLoad( systemMetricsDto ) );
        metrics.setCpuTemp( systemMetricsDtoCpuCpuTemp( systemMetricsDto ) );
        metrics.setMemoryUsage( systemMetricsDtoMemoryMemoryUsage( systemMetricsDto ) );
        metrics.setFreeMemory( systemMetricsDtoMemoryFreeMemory( systemMetricsDto ) );
        metrics.setTotalMemory( systemMetricsDtoMemoryTotalMemory( systemMetricsDto ) );
        metrics.setDiskUsage( systemMetricsDtoDiskDiskUsage( systemMetricsDto ) );
        metrics.setFreeDisk( systemMetricsDtoDiskFreeDisk( systemMetricsDto ) );
        metrics.setTotalDisk( systemMetricsDtoDiskTotalDisk( systemMetricsDto ) );
        metrics.setInterfaceName( systemMetricsDtoNetworkMetricInterfaceName( systemMetricsDto ) );
        metrics.setBytesRecv( systemMetricsDtoNetworkMetricBytesRecv( systemMetricsDto ) );
        metrics.setBytesSent( systemMetricsDtoNetworkMetricBytesSent( systemMetricsDto ) );
        metrics.setInErrors( systemMetricsDtoNetworkMetricInErrors( systemMetricsDto ) );
        metrics.setOutErrors( systemMetricsDtoNetworkMetricOutErrors( systemMetricsDto ) );
        metrics.setOsUptime( systemMetricsDto.getOsUptime() );

        metrics.setServiceUptime( systemMetricsDto.getServiceUptime() != null ? systemMetricsDto.getServiceUptime() / 1000 : null );

        return metrics;
    }

    @Override
    public SystemMetricsResponse toDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        SystemMetricsResponse systemMetricsResponse = new SystemMetricsResponse();

        systemMetricsResponse.setCpu( metricsToCpuMetricResponse( metrics ) );
        systemMetricsResponse.setMemory( metricsToMemoryMetricResponse( metrics ) );
        systemMetricsResponse.setDisk( metricsToDiskMetricResponse( metrics ) );
        systemMetricsResponse.setNetworkMetric( metricsToNetworkMetricResponse( metrics ) );
        systemMetricsResponse.setServiceUptime( metrics.getServiceUptime() );
        systemMetricsResponse.setOsUptime( metrics.getOsUptime() );

        return systemMetricsResponse;
    }

    private Double systemMetricsDtoCpuProcessCpuLoad(SystemMetricsResponse systemMetricsResponse) {
        CpuMetricResponse cpu = systemMetricsResponse.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getProcessCpuLoad();
    }

    private Double systemMetricsDtoCpuSystemCpuLoad(SystemMetricsResponse systemMetricsResponse) {
        CpuMetricResponse cpu = systemMetricsResponse.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getSystemCpuLoad();
    }

    private Double systemMetricsDtoCpuSystemAverageLoad(SystemMetricsResponse systemMetricsResponse) {
        CpuMetricResponse cpu = systemMetricsResponse.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getSystemAverageLoad();
    }

    private Double systemMetricsDtoCpuCpuTemp(SystemMetricsResponse systemMetricsResponse) {
        CpuMetricResponse cpu = systemMetricsResponse.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getCpuTemp();
    }

    private Long systemMetricsDtoMemoryMemoryUsage(SystemMetricsResponse systemMetricsResponse) {
        MemoryMetricResponse memory = systemMetricsResponse.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getMemoryUsage();
    }

    private Long systemMetricsDtoMemoryFreeMemory(SystemMetricsResponse systemMetricsResponse) {
        MemoryMetricResponse memory = systemMetricsResponse.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getFreeMemory();
    }

    private Long systemMetricsDtoMemoryTotalMemory(SystemMetricsResponse systemMetricsResponse) {
        MemoryMetricResponse memory = systemMetricsResponse.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getTotalMemory();
    }

    private Long systemMetricsDtoDiskDiskUsage(SystemMetricsResponse systemMetricsResponse) {
        DiskMetricResponse disk = systemMetricsResponse.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getDiskUsage();
    }

    private Long systemMetricsDtoDiskFreeDisk(SystemMetricsResponse systemMetricsResponse) {
        DiskMetricResponse disk = systemMetricsResponse.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getFreeDisk();
    }

    private Long systemMetricsDtoDiskTotalDisk(SystemMetricsResponse systemMetricsResponse) {
        DiskMetricResponse disk = systemMetricsResponse.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getTotalDisk();
    }

    private String systemMetricsDtoNetworkMetricInterfaceName(SystemMetricsResponse systemMetricsResponse) {
        NetworkMetricResponse networkMetric = systemMetricsResponse.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getInterfaceName();
    }

    private Long systemMetricsDtoNetworkMetricBytesRecv(SystemMetricsResponse systemMetricsResponse) {
        NetworkMetricResponse networkMetric = systemMetricsResponse.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getBytesRecv();
    }

    private Long systemMetricsDtoNetworkMetricBytesSent(SystemMetricsResponse systemMetricsResponse) {
        NetworkMetricResponse networkMetric = systemMetricsResponse.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getBytesSent();
    }

    private Long systemMetricsDtoNetworkMetricInErrors(SystemMetricsResponse systemMetricsResponse) {
        NetworkMetricResponse networkMetric = systemMetricsResponse.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getInErrors();
    }

    private Long systemMetricsDtoNetworkMetricOutErrors(SystemMetricsResponse systemMetricsResponse) {
        NetworkMetricResponse networkMetric = systemMetricsResponse.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getOutErrors();
    }

    protected CpuMetricResponse metricsToCpuMetricResponse(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        CpuMetricResponse cpuMetricResponse = new CpuMetricResponse();

        cpuMetricResponse.setProcessCpuLoad( metrics.getProcessCpuLoad() );
        cpuMetricResponse.setSystemCpuLoad( metrics.getSystemCpuLoad() );
        cpuMetricResponse.setSystemAverageLoad( metrics.getSystemAverageLoad() );
        cpuMetricResponse.setCpuTemp( metrics.getCpuTemp() );

        return cpuMetricResponse;
    }

    protected MemoryMetricResponse metricsToMemoryMetricResponse(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        MemoryMetricResponse memoryMetricResponse = new MemoryMetricResponse();

        memoryMetricResponse.setMemoryUsage( metrics.getMemoryUsage() );
        memoryMetricResponse.setFreeMemory( metrics.getFreeMemory() );
        memoryMetricResponse.setTotalMemory( metrics.getTotalMemory() );

        return memoryMetricResponse;
    }

    protected DiskMetricResponse metricsToDiskMetricResponse(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        DiskMetricResponse diskMetricResponse = new DiskMetricResponse();

        diskMetricResponse.setDiskUsage( metrics.getDiskUsage() );
        diskMetricResponse.setFreeDisk( metrics.getFreeDisk() );
        diskMetricResponse.setTotalDisk( metrics.getTotalDisk() );

        return diskMetricResponse;
    }

    protected NetworkMetricResponse metricsToNetworkMetricResponse(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        NetworkMetricResponse networkMetricResponse = new NetworkMetricResponse();

        networkMetricResponse.setInterfaceName( metrics.getInterfaceName() );
        networkMetricResponse.setBytesRecv( metrics.getBytesRecv() );
        networkMetricResponse.setBytesSent( metrics.getBytesSent() );
        networkMetricResponse.setInErrors( metrics.getInErrors() );
        networkMetricResponse.setOutErrors( metrics.getOutErrors() );

        return networkMetricResponse;
    }
}
