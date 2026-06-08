package com.example.metric_api.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    // CPU
    private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Double cpuTemp;

    // RAM
    private Long memoryUsage;
    private Long freeMemory;
    private Long totalMemory;

    // Disk
    private Long diskUsage;
    private Long freeDisk;
    private Long totalDisk;

    // Network
    private String interfaceName;
    private Long bytesRecv;
    private Long bytesSent;
    private Long inErrors;
    private Long outErrors;

    // uptime
    private Long serviceUptime;
    private Long osUptime;

}
