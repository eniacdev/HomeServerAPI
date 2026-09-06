package com.example.metric_api.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.metric_api.dto.*;
import com.example.metric_api.model.MetricsSnapshot;
import com.example.metric_api.response.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.metric_api.scheduled_job.export.JsonFileBuilder;
import com.example.metric_api.service.IMetricsService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(MetricsControllerImpl.class)
public class MetricsControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private IMetricsService metricsService;
	
	@MockitoBean
	private JsonFileBuilder jsonFileBuilder;
	
	private SystemMetricsLogDto systemMetricsLogDto;
	private OsInfoDto osDto;
	private UptimeMetricDto uptimeDto;
	private CpuMetricDto cpuDto;
	private List<GpuInfoDto> gpuInfoDtoList;
	private GpuInfoDto gpuInfoDto;
	private DiskMetricDto diskDto;
	private MemoryMetricDto memoryDto;
	private NetworkMetricDto networkMetricDto;
	private SystemInfoDto systemInfoDto;
	private MetricsSnapshot snapshot;
	
	
	@BeforeEach
	public void setUp() {
		snapshot = new MetricsSnapshot();
		snapshot.setGeneratedAt(LocalDateTime.of(2026, 9, 6, 11, 00));

		osDto = new OsInfoDto();
		osDto.setOsName("Linux");
		osDto.setOsVersion("Linux-version");

		cpuDto = CpuMetricDto.builder()
				.processCpuLoad(1.5)
				.processCpuLoadFormatted("1.5")
				.systemCpuLoad(1.5)
				.systemCpuLoadFormatted("1.5")
				.systemAverageLoad(1.5)
				.systemAverageLoadFormatted("1.5")
				.cpuTemp("45")
				.build();

		gpuInfoDto = GpuInfoDto.builder()
				.gpuName("gpuTest")
				.vendor("vendorTest")
				.vram(0L)
				.vramFormatted("0")
				.deviceId("deviceIdTest")
				.version("versionTest")
				.build();
		gpuInfoDtoList = new ArrayList<>();
		gpuInfoDtoList.add(gpuInfoDto);

		memoryDto = MemoryMetricDto.builder()
				.memoryUsage(15L - 10L)
				.memoryUsageFormatted("5")
				.freeMemory(10L)
				.freeMemoryFormatted("10")
				.totalMemory(15L)
				.totalMemoryFormatted("10")
				.build();

		diskDto = DiskMetricDto.builder()
				.diskUsage(15L - 10L)
				.diskUsageFormatted("5")
				.freeDisk(10L)
				.freeDiskFormatted("10")
				.totalDisk(15L)
				.totalDiskFormatted("15")
				.build();

		networkMetricDto = NetworkMetricDto.builder()
				.interfaceName("interfaceTest")
				.bytesRecv(0L)
				.bytesRecvFormatted("0")
				.bytesSent(0L)
				.bytesSentFormatted("0")
				.inErrors(0L)
				.outErrors(0L)
				.build();

		systemMetricsLogDto = SystemMetricsLogDto.builder()
				.logId(1L)
				.createdAt(LocalDateTime.now())
				.cpu(cpuDto)
				.memory(memoryDto)
				.disk(diskDto)
				.network(null)
				.uptime(null)
				.build();

		systemInfoDto = SystemInfoDto.builder()
				.uptime(new UptimeMetricDto())
				.hostname("testHost")
				.os(osDto)
				.cpuInfo(new CpuInfoDto())
				.gpuInfo(gpuInfoDtoList)
				.totalMemory(15L)
				.totalMemoryFormatted("15")
				.totalDisk(15L)
				.totalDiskFormatted("15")
				.motherBoard(new MotherBoardInfoDto())
				.bios(new BiosInfoDto())
				.network(new NetworkInfoDto())
				.build();
	}
	
	@Test
	public void saveAndGetMetricsTest() throws Exception{
		when(metricsService.saveMetrics()).thenReturn(systemMetricsLogDto);
		
		mockMvc.perform(post("/api/v1/metrics/save"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.data.cpu.processCpuLoad").value(1.5))
        .andDo(print());

		verify(metricsService).saveMetrics();
	}
	
	@Test
	public void getCpuMetricTest() throws Exception{
		when(metricsService.getCpuMetric()).thenReturn(cpuDto);
		
		mockMvc.perform(get("/api/v1/metrics/cpu"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.processCpuLoad").value(1.5))
		.andDo(print());
		
		verify(metricsService).getCpuMetric();
		
	}
	
	@Test
	public void getMemoryMetricTest() throws Exception{
		when(metricsService.getMemoryMetric()).thenReturn(memoryDto);
		
		mockMvc.perform(get("/api/v1/metrics/memory"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.data.totalMemory").value(15L))
		.andDo(print());
		
		verify(metricsService).getMemoryMetric();
	}

	@Test
	public void getDiskMetricTest() throws Exception{
		when(metricsService.getDiskMetric()).thenReturn(diskDto);

		mockMvc.perform(get("/api/v1/metrics/disk"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.totalDisk").value(15L))
				.andDo(print());

		verify(metricsService).getDiskMetric();
	}

	@Test
	public void getSystemInfoTest() throws Exception{
		when(metricsService.getSystemInfo()).thenReturn(systemInfoDto);

		mockMvc.perform(get("/api/v1/metrics/system"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.hostname").value("testHost"))
				.andDo(print());

		verify(metricsService).getSystemInfo();
	}

	@Test
	public void deleteLogByIdTest() throws Exception{
		when(metricsService.deleteLogById(1)).thenReturn(true);

		mockMvc.perform(delete("/api/v1/metrics/log/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(true))
				.andDo(print());

		verify(metricsService).deleteLogById(1);
	}

	@Test
	public void buildSnapshotTest() throws Exception{
		when(metricsService.buildSnapshot()).thenReturn(snapshot);

		mockMvc.perform(get("/api/v1/metrics/snapshot"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.generatedAt").value("2026-09-06T11:00:00"))
				.andDo(print());

		verify(metricsService).buildSnapshot();
	}

	@Test
	public void getGpuInfoTest() throws Exception{
		when(metricsService.getGpuInfo()).thenReturn(gpuInfoDtoList);

		mockMvc.perform(get("/api/v1/metrics/gpu"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data[0].gpuName").value("gpuTest"))
				.andDo(print());

		verify(metricsService).getGpuInfo();
	}

	@Test
	public void getNetworkMetricTest() throws Exception{
		when(metricsService.getNetworkMetric()).thenReturn(networkMetricDto);

		mockMvc.perform(get("/api/v1/metrics/network"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.interfaceName").value("interfaceTest"))
				.andDo(print());

		verify(metricsService).getNetworkMetric();
	}
}