package com.example.metric_api.entitiy;

import com.example.metric_api.model.CpuMetric;
import com.example.metric_api.model.DiskMetric;
import com.example.metric_api.model.MemoryMetric;
import com.example.metric_api.model.NetworkMetric;
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

    @Embedded
    private CpuMetric cpuMetric;

    @Embedded
    private MemoryMetric memoryMetric;

    @Embedded
    private DiskMetric diskMetric;

    @Embedded
    private NetworkMetric networkMetric;

    private Long serviceUptime;
    private Long osUptime;
}
