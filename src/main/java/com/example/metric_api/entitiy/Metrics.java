package com.example.metric_api.entitiy;

import com.example.metric_api.model.OsInfoDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Table(name = "metrics")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    private LocalDateTime createdAt;

    //CPU
    private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Double cpuTemp;
	private Double cpuVolt;
	private int[] fanSpeeds;

    //RAM
    private Long memoryUsage;
    private Long freeMemory;
    private Long totalMemory;

    //Disk
    private Long diskUsage;
    private Long freeDisk;
    private Long totalDisk;

    //uptime
    private Long serviceUptime;
    private Long osUptime;

}
