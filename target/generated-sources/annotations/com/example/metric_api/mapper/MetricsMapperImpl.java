package com.example.metric_api.mapper;

import com.example.metric_api.dto.CpuMetricDto;
import com.example.metric_api.dto.DiskMetricDto;
import com.example.metric_api.dto.MemoryMetricDto;
import com.example.metric_api.dto.NetworkMetricDto;
import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.CpuMetric;
import com.example.metric_api.model.DiskMetric;
import com.example.metric_api.model.MemoryMetric;
import com.example.metric_api.model.NetworkMetric;
import com.example.metric_api.model.SystemMetrics;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-08-14T11:52:23+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.3 (JetBrains s.r.o.)"
)
@Component
public class MetricsMapperImpl implements MetricsMapper {

    @Override
    public Metrics toEntity(SystemMetrics systemMetricsDto) {
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
    public SystemMetricsDto toDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        SystemMetricsDto systemMetricsDto = new SystemMetricsDto();

        systemMetricsDto.setCpu( metricsToCpuMetricDto( metrics ) );
        systemMetricsDto.setMemory( metricsToMemoryMetricDto( metrics ) );
        systemMetricsDto.setDisk( metricsToDiskMetricDto( metrics ) );
        systemMetricsDto.setNetworkMetric( metricsToNetworkMetricDto( metrics ) );
        systemMetricsDto.setServiceUptime( metrics.getServiceUptime() );
        systemMetricsDto.setOsUptime( metrics.getOsUptime() );

        return systemMetricsDto;
    }

    @Override
    public SystemMetricsDto toDto(SystemMetrics systemMetrics) {
        if ( systemMetrics == null ) {
            return null;
        }

        SystemMetricsDto systemMetricsDto = new SystemMetricsDto();

        systemMetricsDto.setCpu( cpuMetricToCpuMetricDto( systemMetrics.getCpu() ) );
        systemMetricsDto.setMemory( memoryMetricToMemoryMetricDto( systemMetrics.getMemory() ) );
        systemMetricsDto.setDisk( diskMetricToDiskMetricDto( systemMetrics.getDisk() ) );
        systemMetricsDto.setNetworkMetric( networkMetricToNetworkMetricDto( systemMetrics.getNetworkMetric() ) );
        systemMetricsDto.setServiceUptime( systemMetrics.getServiceUptime() );
        systemMetricsDto.setOsUptime( systemMetrics.getOsUptime() );

        return systemMetricsDto;
    }

    private Double systemMetricsDtoCpuProcessCpuLoad(SystemMetrics systemMetrics) {
        CpuMetric cpu = systemMetrics.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getProcessCpuLoad();
    }

    private Double systemMetricsDtoCpuSystemCpuLoad(SystemMetrics systemMetrics) {
        CpuMetric cpu = systemMetrics.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getSystemCpuLoad();
    }

    private Double systemMetricsDtoCpuSystemAverageLoad(SystemMetrics systemMetrics) {
        CpuMetric cpu = systemMetrics.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getSystemAverageLoad();
    }

    private Double systemMetricsDtoCpuCpuTemp(SystemMetrics systemMetrics) {
        CpuMetric cpu = systemMetrics.getCpu();
        if ( cpu == null ) {
            return null;
        }
        return cpu.getCpuTemp();
    }

    private Long systemMetricsDtoMemoryMemoryUsage(SystemMetrics systemMetrics) {
        MemoryMetric memory = systemMetrics.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getMemoryUsage();
    }

    private Long systemMetricsDtoMemoryFreeMemory(SystemMetrics systemMetrics) {
        MemoryMetric memory = systemMetrics.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getFreeMemory();
    }

    private Long systemMetricsDtoMemoryTotalMemory(SystemMetrics systemMetrics) {
        MemoryMetric memory = systemMetrics.getMemory();
        if ( memory == null ) {
            return null;
        }
        return memory.getTotalMemory();
    }

    private Long systemMetricsDtoDiskDiskUsage(SystemMetrics systemMetrics) {
        DiskMetric disk = systemMetrics.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getDiskUsage();
    }

    private Long systemMetricsDtoDiskFreeDisk(SystemMetrics systemMetrics) {
        DiskMetric disk = systemMetrics.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getFreeDisk();
    }

    private Long systemMetricsDtoDiskTotalDisk(SystemMetrics systemMetrics) {
        DiskMetric disk = systemMetrics.getDisk();
        if ( disk == null ) {
            return null;
        }
        return disk.getTotalDisk();
    }

    private String systemMetricsDtoNetworkMetricInterfaceName(SystemMetrics systemMetrics) {
        NetworkMetric networkMetric = systemMetrics.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getInterfaceName();
    }

    private Long systemMetricsDtoNetworkMetricBytesRecv(SystemMetrics systemMetrics) {
        NetworkMetric networkMetric = systemMetrics.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getBytesRecv();
    }

    private Long systemMetricsDtoNetworkMetricBytesSent(SystemMetrics systemMetrics) {
        NetworkMetric networkMetric = systemMetrics.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getBytesSent();
    }

    private Long systemMetricsDtoNetworkMetricInErrors(SystemMetrics systemMetrics) {
        NetworkMetric networkMetric = systemMetrics.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getInErrors();
    }

    private Long systemMetricsDtoNetworkMetricOutErrors(SystemMetrics systemMetrics) {
        NetworkMetric networkMetric = systemMetrics.getNetworkMetric();
        if ( networkMetric == null ) {
            return null;
        }
        return networkMetric.getOutErrors();
    }

    protected CpuMetricDto metricsToCpuMetricDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        CpuMetricDto cpuMetricDto = new CpuMetricDto();

        cpuMetricDto.setProcessCpuLoad( metrics.getProcessCpuLoad() );
        cpuMetricDto.setSystemCpuLoad( metrics.getSystemCpuLoad() );
        cpuMetricDto.setSystemAverageLoad( metrics.getSystemAverageLoad() );
        cpuMetricDto.setCpuTemp( metrics.getCpuTemp() );

        return cpuMetricDto;
    }

    protected MemoryMetricDto metricsToMemoryMetricDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        MemoryMetricDto memoryMetricDto = new MemoryMetricDto();

        memoryMetricDto.setMemoryUsage( metrics.getMemoryUsage() );
        memoryMetricDto.setFreeMemory( metrics.getFreeMemory() );
        memoryMetricDto.setTotalMemory( metrics.getTotalMemory() );

        return memoryMetricDto;
    }

    protected DiskMetricDto metricsToDiskMetricDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        DiskMetricDto diskMetricDto = new DiskMetricDto();

        diskMetricDto.setDiskUsage( metrics.getDiskUsage() );
        diskMetricDto.setFreeDisk( metrics.getFreeDisk() );
        diskMetricDto.setTotalDisk( metrics.getTotalDisk() );

        return diskMetricDto;
    }

    protected NetworkMetricDto metricsToNetworkMetricDto(Metrics metrics) {
        if ( metrics == null ) {
            return null;
        }

        NetworkMetricDto networkMetricDto = new NetworkMetricDto();

        networkMetricDto.setInterfaceName( metrics.getInterfaceName() );
        networkMetricDto.setBytesRecv( metrics.getBytesRecv() );
        networkMetricDto.setBytesSent( metrics.getBytesSent() );
        networkMetricDto.setInErrors( metrics.getInErrors() );
        networkMetricDto.setOutErrors( metrics.getOutErrors() );

        return networkMetricDto;
    }

    protected CpuMetricDto cpuMetricToCpuMetricDto(CpuMetric cpuMetric) {
        if ( cpuMetric == null ) {
            return null;
        }

        CpuMetricDto cpuMetricDto = new CpuMetricDto();

        cpuMetricDto.setProcessCpuLoad( cpuMetric.getProcessCpuLoad() );
        cpuMetricDto.setSystemCpuLoad( cpuMetric.getSystemCpuLoad() );
        cpuMetricDto.setSystemAverageLoad( cpuMetric.getSystemAverageLoad() );
        cpuMetricDto.setCpuTemp( cpuMetric.getCpuTemp() );

        return cpuMetricDto;
    }

    protected MemoryMetricDto memoryMetricToMemoryMetricDto(MemoryMetric memoryMetric) {
        if ( memoryMetric == null ) {
            return null;
        }

        MemoryMetricDto memoryMetricDto = new MemoryMetricDto();

        memoryMetricDto.setMemoryUsage( memoryMetric.getMemoryUsage() );
        memoryMetricDto.setFreeMemory( memoryMetric.getFreeMemory() );
        memoryMetricDto.setTotalMemory( memoryMetric.getTotalMemory() );

        return memoryMetricDto;
    }

    protected DiskMetricDto diskMetricToDiskMetricDto(DiskMetric diskMetric) {
        if ( diskMetric == null ) {
            return null;
        }

        DiskMetricDto diskMetricDto = new DiskMetricDto();

        diskMetricDto.setDiskUsage( diskMetric.getDiskUsage() );
        diskMetricDto.setFreeDisk( diskMetric.getFreeDisk() );
        diskMetricDto.setTotalDisk( diskMetric.getTotalDisk() );

        return diskMetricDto;
    }

    protected NetworkMetricDto networkMetricToNetworkMetricDto(NetworkMetric networkMetric) {
        if ( networkMetric == null ) {
            return null;
        }

        NetworkMetricDto networkMetricDto = new NetworkMetricDto();

        networkMetricDto.setInterfaceName( networkMetric.getInterfaceName() );
        networkMetricDto.setBytesRecv( networkMetric.getBytesRecv() );
        networkMetricDto.setBytesSent( networkMetric.getBytesSent() );
        networkMetricDto.setInErrors( networkMetric.getInErrors() );
        networkMetricDto.setOutErrors( networkMetric.getOutErrors() );

        return networkMetricDto;
    }
}
