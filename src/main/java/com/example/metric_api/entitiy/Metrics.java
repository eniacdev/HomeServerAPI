package com.example.metric_api.entitiy;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "metrics")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Metrics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // CPU
    private Double processCpuLoad;
	private Double systemCpuLoad;
	private Double systemAverageLoad;
	private Integer cpuTemp;

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
