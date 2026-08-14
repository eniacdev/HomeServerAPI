package com.example.metric_api.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.metric_api.dto.CpuMetricDto;
import com.example.metric_api.dto.DiskMetricDto;
import com.example.metric_api.dto.MemoryMetricDto;
import com.example.metric_api.dto.OsInfoDto;
import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.dto.UptimeMetricDto;
import com.example.metric_api.scheduled_job.export.PrepareJsonFile;
import com.example.metric_api.service.IMetricsService;

@WebMvcTest(MetricsControllerImpl.class)
public class MetricsControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private IMetricsService metricsService;
	
	@MockitoBean
	private PrepareJsonFile prepareJsonFile;
	
	SystemMetricsDto metric = new SystemMetricsDto();
	OsInfoDto os = new OsInfoDto();
	UptimeMetricDto uptime = new UptimeMetricDto();
	CpuMetricDto cpu = new CpuMetricDto();
	DiskMetricDto disk = new DiskMetricDto();
	MemoryMetricDto memory = new MemoryMetricDto();
	
	
	@BeforeEach
	public void setUp() {
		
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
		
		metric.setCpu(cpu);
		metric.setDisk(disk);
		metric.setMemory(memory);
	}
	
	@Test
	public void prepareAndCreateMetricsTest() throws Exception{
		
		//when
		//when(metricsService.prepareAndSaveMetrics()).thenReturn(metric);
		
		mockMvc.perform(post("/api/v1/metrics/collect"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.cpu.processCpuLoad").value(1.5))
        .andDo(print());
		
		//then
		verify(metricsService).getMetrics();
		
	}
	
	@Test
	public void getCpuMetricTest() throws Exception{
		
		//when(metricsService.getCpuMetric()).thenReturn(cpu);
		
		mockMvc.perform(get("/api/v1/metrics/cpu"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.processCpuLoad").value(1.5))
		.andDo(print());
		
		verify(metricsService).getCpuMetric();
		
	}
	
	@Test
	public void getMemoryMetricTest() throws Exception{
		
		//when(metricsService.getMemoryMetric()).thenReturn(memory);
		
		mockMvc.perform(get("/api/v1/metrics/memory"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.totalMemory").value(15L))
		.andDo(print());
		
		verify(metricsService).getMemoryMetric();
	}
}