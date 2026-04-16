package com.example.metric_api.service;

import com.example.metric_api.controller.MetricsControllerImpl;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.*;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.scheduled_job.prepare.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class MetricServiceImplTest {

    @InjectMocks
    private MetricServiceImpl metricsService;

    @Mock
    private PrepareJsonFile prepareJsonFile;

    @Mock
    private PrepareSystemMetrics prepareSystemMetrics;

    @Mock
    private PrepareCpuMetric prepareCpuMetric;

    @Mock
    private PrepareMemoryMetric prepareMemoryMetric;

    @Mock
    private PrepareDiskMetric prepareDiskMetric;

    @Mock
    private PrepareSystemInfo prepareSystemInfo;

    SystemMetricsDto metrics = new SystemMetricsDto();
    CpuDto cpu = new CpuDto();
    MemoryDto memory = new MemoryDto();
    DiskDto disk = new DiskDto();
    OsDto os = new OsDto();
    UptimeMetricDto uptime = new UptimeMetricDto();
    SystemInfoDto systemInfo = new SystemInfoDto();


    @BeforeEach
    public void setUp(){

        os.setOsName("Linux");
        os.setOsVersion("Linux-version");

        cpu.setCpuCores(2);
        cpu.setProcessCpuLoad(1.5);
        cpu.setSystemAverageLoad(1.5);
        cpu.setSystemCpuLoad(1.5);

        memory.setFreeMemory(10L);
        memory.setTotalMemory(15L);
        memory.setMemoryUsage(memory.getTotalMemory() - memory.getFreeMemory());

        disk.setFreeDisk(10L);
        disk.setTotalDisk(10L);
        disk.setDiskUsage(disk.getTotalDisk() - disk.getFreeDisk());

        uptime.setOsUpTime(100L);
        uptime.setServiceUpTime(100L);

        systemInfo.setHostname("Linux");
        systemInfo.setOs(os);
        systemInfo.setUptime(uptime);

        metrics.setCpu(cpu);
        metrics.setDisk(disk);
        metrics.setMemory(memory);
    }

    @Test
    public void prepareAndSaveMetrics() throws  Exception{
        when(prepareSystemMetrics.prepareSystemMetrics()).thenReturn(metrics);
        when(prepareJsonFile.writeJsonFile(metrics)).thenReturn(true);

        SystemMetricsDto result = metricsService.prepareAndSaveMetrics();

        assertNotNull(result);
        assertEquals(metrics.getCpu(), result.getCpu());
        assertEquals(metrics.getMemory(), result.getMemory());
        assertEquals(metrics.getDisk(), result.getDisk());

        verify(prepareSystemMetrics).prepareSystemMetrics();
        verify(prepareJsonFile).writeJsonFile(metrics);

    }

    @Test
    public void getSystemInfoTest() throws Exception{

        when(prepareSystemInfo.collectSystemInfo()).thenReturn(systemInfo);

        SystemInfoDto result = metricsService.prepareAndGetSystemInfo();

        assertNotNull(result);
        assertEquals(systemInfo.getOs(), result.getOs());
        assertEquals(systemInfo.getUptime(), result.getUptime());
        assertEquals(systemInfo.getHostname(), result.getHostname());

        verify(prepareSystemInfo).collectSystemInfo();
    }

    @Test
    public void getCpuMetricTest(){

        when(prepareCpuMetric.collectCpuMetrics()).thenReturn(cpu);

        CpuDto result = metricsService.getCpuMetric();

        assertNotNull(result);
        assertEquals(cpu.getCpuCores(), result.getCpuCores());
        assertEquals(cpu.getSystemCpuLoad(), result.getSystemCpuLoad());
        assertEquals(cpu.getProcessCpuLoad(), result.getProcessCpuLoad());
        assertEquals(cpu.getSystemAverageLoad(), result.getSystemAverageLoad());

        verify(prepareCpuMetric).collectCpuMetrics();

    }

    @Test
    public void getMemoryMetricTest() {

        when(prepareMemoryMetric.collectMemoryMetrics()).thenReturn(memory);

        MemoryDto result = metricsService.getMemoryMetric();

        assertNotNull(result);
        assertEquals(memory.getFreeMemory(), result.getFreeMemory());
        assertEquals(memory.getTotalMemory(), result.getTotalMemory());
        assertEquals(memory.getMemoryUsage(), result.getMemoryUsage());

        verify(prepareMemoryMetric).collectMemoryMetrics();
    }

    @Test
    public void getDiskMetricTest(){

        when(prepareDiskMetric.collectDiskMetrics()).thenReturn(disk);

        DiskDto result = metricsService.getDiskMetric();

        assertNotNull(result);
        assertEquals(disk.getFreeDisk(), result.getFreeDisk());
        assertEquals(disk.getDiskUsage(), result.getDiskUsage());
        assertEquals(disk.getTotalDisk(), result.getTotalDisk());

        verify(prepareDiskMetric).collectDiskMetrics();
    }

    //exception test
    @Test
    public void throwMetricNotCollectedExceptionTest() throws Exception{
        when(prepareSystemMetrics.prepareSystemMetrics()).thenThrow(new RuntimeException());

        BaseException ex = assertThrows(BaseException.class, () ->{
           metricsService.prepareAndSaveMetrics();
        });

        assertEquals("Something went wrong, metrics not collected.", ex.getMessage());

        verify(prepareSystemMetrics).prepareSystemMetrics();
    }
}
