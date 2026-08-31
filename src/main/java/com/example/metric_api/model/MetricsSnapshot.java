package com.example.metric_api.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MetricsSnapshot {
    private StaticMetric staticMetric;
    private CurrentMetric currentMetric;
    private LocalDateTime generatedAt;
}
