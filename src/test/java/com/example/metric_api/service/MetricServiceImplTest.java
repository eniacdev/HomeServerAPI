package com.example.metric_api.service;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.dto.*;
import com.example.metric_api.model.*;
import com.example.metric_api.repository.IMetricsRepository;
import com.example.metric_api.scheduled_job.prepare.info.SystemInfoCollector;
import com.example.metric_api.scheduled_job.prepare.info.UptimeInfoCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.CpuMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.DiskMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.MemoryMetricCollector;
import com.example.metric_api.scheduled_job.prepare.metrics.SystemMetricsCollector;
import com.example.metric_api.entitiy.Metrics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
public class MetricServiceImplTest {

    @InjectMocks
    private MetricServiceImpl metricsService;

    @Mock
    private UptimeInfoCollector uptimeInfo;

    @Mock
    private IMetricsRepository metricsRepository;

    @Mock
    private SystemMetricsCollector collectSystemMetrics;

    @Mock
    private CpuMetricCollector cpuMetricCollector;

    @Mock
    private MemoryMetricCollector collectMemoryMetric;

    @Mock
    private DiskMetricCollector collectDiskMetric;

    @Mock
    private SystemInfoCollector systemInfoCollector;

    SystemMetrics systemMetrics = new SystemMetrics();
    Metrics metrics = new Metrics();
    CpuMetric cpu = new CpuMetric();
    MemoryMetric memory = new MemoryMetric();
    DiskMetric disk = new DiskMetric();
    OsInfo os = new OsInfo();
    UptimeMetric uptime = new UptimeMetric();
    SystemInfo systemInfo = new SystemInfo();


    @BeforeEach
    public void setUp(){

        os.setOsName("Linux");
        os.setOsVersion("Linux-version");

        cpu.setProcessCpuLoad(1.5);
        cpu.setSystemAverageLoad(1.5);
        cpu.setSystemCpuLoad(1.5);

        memory.setFreeMemory(10L);
        memory.setTotalMemory(15L);
        memory.setMemoryUsage(memory.getTotalMemory() - memory.getFreeMemory());

        disk.setFreeDisk(10L);
        disk.setTotalDisk(10L);
        disk.setDiskUsage(disk.getTotalDisk() - disk.getFreeDisk());

        uptime.setOsUptime(100L);
        uptime.setServiceUptime(100L);

        systemInfo.setHostname("Linux");
        systemInfo.setOs(os);
        systemInfo.setUptime(uptime);

        systemMetrics.setCpuMetric(cpu);
        systemMetrics.setDiskMetric(disk);
        systemMetrics.setMemoryMetric(memory);
    }

    /*@Test
    public void prepareAndSaveMetrics() throws  Exception{

        when(collectSystemMetrics.prepareSystemMetrics()).thenReturn(systemMetrics);

        SystemMetricsDto result = metricsService.saveMetrics();

        assertNotNull(result);
        assertEquals(systemMetrics.getCpu(), result.getCpu());
        assertEquals(systemMetrics.getMemory(), result.getMemory());
        assertEquals(systemMetrics.getDisk(), result.getDisk());

        verify(collectSystemMetrics).prepareSystemMetrics();
        verify(metricsRepository).save(any(Metrics.class));
    }*/

    @Test
    public void getSystemInfoTest() throws Exception{

        when(systemInfoCollector.collectSystemInfo()).thenReturn(systemInfo);

        SystemInfoDto result = metricsService.getSystemInfo();

        assertNotNull(result);
        assertEquals(systemInfo.getOs(), result.getOs());
        assertEquals(systemInfo.getUptime(), result.getUptime());
        assertEquals(systemInfo.getHostname(), result.getHostname());

        verify(systemInfoCollector).collectSystemInfo();
    }

    @Test
    public void getCpuMetricTest(){

        when(cpuMetricCollector.collectCpuMetrics()).thenReturn(cpu);

        CpuMetricDto result = metricsService.getCpuMetric();

        assertNotNull(result);
        assertEquals(cpu.getSystemCpuLoad(), result.getSystemCpuLoad());
        assertEquals(cpu.getProcessCpuLoad(), result.getProcessCpuLoad());
        assertEquals(cpu.getSystemAverageLoad(), result.getSystemAverageLoad());

        verify(cpuMetricCollector).collectCpuMetrics();

    }

    @Test
    public void getMemoryMetricTest() {

        when(collectMemoryMetric.collectMemoryMetrics()).thenReturn(memory);

        MemoryMetricDto result = metricsService.getMemoryMetric();

        assertNotNull(result);
        assertEquals(memory.getFreeMemory(), result.getFreeMemory());
        assertEquals(memory.getMemoryUsage(), result.getMemoryUsage());

        verify(collectMemoryMetric).collectMemoryMetrics();
    }

    @Test
    public void getDiskMetricTest(){

        when(collectDiskMetric.collectDiskMetrics()).thenReturn(disk);

        DiskMetricDto result = metricsService.getDiskMetric();

        assertNotNull(result);
        assertEquals(disk.getFreeDisk(), result.getFreeDisk());
        assertEquals(disk.getDiskUsage(), result.getDiskUsage());

        verify(collectDiskMetric).collectDiskMetrics();
    }

    @Test
    public void deleteLogById() throws Exception{
        when(metricsRepository.findById(1L)).thenReturn(Optional.of(metrics));

        Boolean result = metricsService.deleteLogById(1L);

        assertTrue(result);

        verify(metricsRepository).findById(1L);
        verify(metricsRepository).deleteById(1L);
    }

    /*@Test
    public void getLogByIdTest() throws Exception{
        when(metricsRepository.findById(1L)).thenReturn(Optional.of(metrics));

        SystemMetricsDto result = metricsService.getLogById(1L);

        assertNotNull(result);
        
        verify(metricsRepository).findById(1L);

    }*/

    //exception test
    @Test
    public void throwMetricNotCollectedExceptionTest() throws Exception{
        when(collectSystemMetrics.prepareSystemMetrics()).thenThrow(new RuntimeException());

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
