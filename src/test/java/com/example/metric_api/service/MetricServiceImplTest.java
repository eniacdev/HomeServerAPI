package com.example.metric_api.service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.dto.*;
import com.example.metric_api.mapper.*;
import com.example.metric_api.model.*;
import com.example.metric_api.repository.IMetricsRepository;
import com.example.metric_api.scheduled_job.collector.info.SystemInfoCollector;
import com.example.metric_api.scheduled_job.collector.info.UptimeInfoCollector;
import com.example.metric_api.scheduled_job.collector.metrics.*;
import com.example.metric_api.entitiy.Metrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class MetricServiceImplTest {

    @InjectMocks
    private MetricServiceImpl metricsService;

    // mappers
    @Mock
    private SystemInfoMapper systemInfoMapper;

    @Mock
    private MetricsMapper metricsMapper;

    @Mock
    private CpuMapper cpuMapper;

    @Mock
    private MemoryMapper memoryMapper;

    @Mock
    private DiskMapper diskMapper;

    @Mock
    private NetworkMapper networkMapper;

    @Mock
    private UptimeInfoCollector uptimeInfo;

    @Mock
    private IMetricsRepository metricsRepository;

    @Mock
    private SystemMetricsCollector systemMetricsCollector;

    @Mock
    private CpuMetricCollector cpuMetricCollector;

    @Mock
    private MemoryMetricCollector memoryMetricCollector;

    @Mock
    private DiskMetricCollector diskMetricCollector;

    @Mock
    private SystemInfoCollector systemInfoCollector;

    @Mock
    private NetworkMetricCollector networkMetricCollector;

    private SystemMetricsLog systemMetrics = new SystemMetricsLog();
    private Metrics metrics = new Metrics();
    private CpuMetric cpu = new CpuMetric();
    private MemoryMetric memory = new MemoryMetric();
    private DiskMetric disk = new DiskMetric();
    private NetworkMetric networkMetric;

    private OsInfo os = new OsInfo();
    private UptimeMetric uptime = new UptimeMetric();
    private SystemInfo systemInfo;

    private SystemMetricsLogDto systemMetricsLogDto;
    private CpuMetricDto cpuDto;
    private MemoryMetricDto memoryDto;
    private DiskMetricDto diskDto;
    private NetworkMetricDto networkDto;
    private SystemInfoDto systemInfoDto;

    @BeforeEach
    public void setUp(){

        //entity
        os.setOsName("Linux");
        os.setOsVersion("Linux-version");

        cpu.setProcessCpuLoad(1.5);
        cpu.setSystemAverageLoad(1.5);
        cpu.setSystemCpuLoad(1.5);
        cpu.setCpuTemp(45);

        memory.setFreeMemory(10L);
        memory.setTotalMemory(10L);
        memory.setMemoryUsage(memory.getTotalMemory() - memory.getFreeMemory());

        disk.setFreeDisk(10L);
        disk.setTotalDisk(10L);
        disk.setDiskUsage(disk.getTotalDisk() - disk.getFreeDisk());

        uptime.setOsUptime(100L);
        uptime.setServiceUptime(100L);

        systemMetrics.setCpuMetric(cpu);
        systemMetrics.setDiskMetric(disk);
        systemMetrics.setMemoryMetric(memory);


        // dtos
        cpuDto = CpuMetricDto.builder()
                .processCpuLoad(cpu.getProcessCpuLoad())
                .processCpuLoadFormatted("1.5")
                .systemCpuLoad(cpu.getSystemCpuLoad())
                .systemCpuLoadFormatted("1.5")
                .systemAverageLoad(cpu.getSystemAverageLoad())
                .systemAverageLoadFormatted("1.5")
                .cpuTemp(String.valueOf(cpu.getCpuTemp()))
                .build();

        memoryDto = MemoryMetricDto.builder()
                .memoryUsage(10L)
                .memoryUsageFormatted("10")
                .freeMemory(10L)
                .freeMemoryFormatted("10")
                .totalMemory(0L)
                .totalMemoryFormatted("0")
                .build();

        diskDto = DiskMetricDto.builder()
                .diskUsage(10L)
                .diskUsageFormatted("10")
                .freeDisk(10L)
                .freeDiskFormatted("10")
                .totalDisk(0L)
                .totalDiskFormatted("0")
                .build();

        systemMetricsLogDto = SystemMetricsLogDto.builder()
                .cpu(cpuDto)
                .memory(memoryDto)
                .disk(diskDto)
                .network(new NetworkMetricDto())
                .uptime(new UptimeMetricDto())
                .build();

       systemInfoDto = SystemInfoDto.builder()
                .uptime(new UptimeMetricDto())
                .hostname("testHost")
                .os(new OsInfoDto())
                .cpuInfo(new CpuInfoDto())
                .totalMemory(0L)
                .totalDisk(0L)
                .motherBoard(new MotherBoardInfoDto())
                .bios(new BiosInfoDto())
                .network(new NetworkInfoDto())
                .build();

        systemInfo = SystemInfo.builder()
                .uptime(uptime)
                .hostname("Linux")
                .os(os)
                .cpuInfo(new CpuInfo())
                .totalMemory(0L)
                .totalDisk(0L)
                .motherBoard(new MotherBoardInfo())
                .bios(new BiosInfo())
                .network(new NetworkInfo())
                .build();

        networkMetric = NetworkMetric.builder()
                .interfaceName("internetTest")
                .bytesRecv(0L)
                .bytesSent(0L)
                .inErrors(0L)
                .outErrors(0L)
                .build();

        networkDto = NetworkMetricDto.builder()
                .interfaceName("internetTest")
                .bytesRecv(0L)
                .bytesRecvFormatted("0")
                .bytesSent(0L)
                .bytesSentFormatted("0")
                .inErrors(0L)
                .outErrors(0L)
                .build();
    }

    @Test
    public void SaveMetricsTest() throws  Exception{
        when(systemMetricsCollector.prepareSystemMetrics()).thenReturn(systemMetrics);
        when(metricsMapper.toDtoLog(metrics)).thenReturn(systemMetricsLogDto);
        when(metricsMapper.toEntity(systemMetrics)).thenReturn(metrics);
        when(metricsRepository.save(metrics)).thenReturn(metrics);

        SystemMetricsLogDto result = metricsService.saveMetrics();

        assertNotNull(result);
        assertSame(systemMetricsLogDto, result);

        verify(systemMetricsCollector).prepareSystemMetrics();
        verify(metricsMapper).toDtoLog(metrics);
        verify(metricsRepository).save(any(Metrics.class));
    }

    @Test
    public void getSystemInfoTest() throws Exception{
        when(systemInfoCollector.collectSystemInfo()).thenReturn(systemInfo);
        when(systemInfoMapper.toDto(systemInfo)).thenReturn(systemInfoDto);

        SystemInfoDto result = metricsService.getSystemInfo();

        assertNotNull(result);
        assertSame(systemInfoDto, result);

        verify(systemInfoCollector).collectSystemInfo();
        verify(systemInfoMapper).toDto(systemInfo);
    }

    @Test
    public void getCpuMetricTest(){
        when(cpuMetricCollector.collectCpuMetrics()).thenReturn(cpu);
        when(cpuMapper.toDto(cpu)).thenReturn(cpuDto);

        CpuMetricDto result = metricsService.getCpuMetric();

        assertNotNull(result);
        assertSame(cpuDto, result);

        verify(cpuMetricCollector).collectCpuMetrics();
        verify(cpuMapper).toDto(cpu);
    }

    @Test
    public void getMemoryMetricTest() {
        when(memoryMetricCollector.collectMemoryMetrics()).thenReturn(memory);
        when(memoryMapper.toDto(memory)).thenReturn(memoryDto);

        MemoryMetricDto result = metricsService.getMemoryMetric();

        assertNotNull(result);
        assertSame(memoryDto, result);

        verify(memoryMetricCollector).collectMemoryMetrics();
        verify(memoryMapper).toDto(memory);
    }

    @Test
    public void getDiskMetricTest(){
        when(diskMetricCollector.collectDiskMetrics()).thenReturn(disk);
        when(diskMapper.toDto(disk)).thenReturn(diskDto);

        DiskMetricDto result = metricsService.getDiskMetric();

        assertNotNull(result);
        assertSame(diskDto, result);

        verify(diskMetricCollector).collectDiskMetrics();
        verify(diskMapper).toDto(disk);
    }

    @Test
    public void getNetworkMetricTest(){
        when(networkMetricCollector.collectNetworkMetric()).thenReturn(networkMetric);
        when(networkMapper.toDto(networkMetric)).thenReturn(networkDto);

        NetworkMetricDto result = metricsService.getNetworkMetric();

        assertNotNull(result);
        assertSame(networkDto, result);

        verify(networkMetricCollector).collectNetworkMetric();
        verify(networkMapper).toDto(networkMetric);
    }

    @Test
    public void deleteLogById() throws Exception{
        when(metricsRepository.findById(1L)).thenReturn(Optional.of(metrics));

        Boolean result = metricsService.deleteLogById(1L);

        assertTrue(result);

        verify(metricsRepository).findById(1L);
        verify(metricsRepository).deleteById(1L);
    }

    @Test
    public void getLogByIdTest() throws Exception{
        when(metricsRepository.findById(1L)).thenReturn(Optional.of(metrics));
        when(metricsMapper.toDtoLog(metrics)).thenReturn(systemMetricsLogDto);

        SystemMetricsLogDto result = metricsService.getLogById(1L);

        assertNotNull(result);

        verify(metricsRepository).findById(1L);
        verify(metricsMapper).toDtoLog(metrics);
    }

    //exception test
    @Test
    public void throwMetricNotCollectedExceptionTest() throws Exception{
        when(systemMetricsCollector.prepareSystemMetrics()).thenThrow(new RuntimeException());

        BaseException ex = assertThrows(BaseException.class, () ->{
           metricsService.saveMetrics();
        });

        assertEquals("Something went wrong, metrics not collected.", ex.getMessage());

    }

    @Test
    public void throwMetricsNotCollectedExceptionTest() throws Exception{
        when(metricsRepository.findById(0L)).thenReturn(Optional.empty());

        BaseException ex = assertThrows(BaseException.class, () ->{
            metricsService.getLogById(0L);
        });

        assertEquals("Something went wrong, metrics is not found.", ex.getMessage());

    }
}
