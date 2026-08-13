package com.example.metric_api.repository;

import com.example.metric_api.entitiy.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMetricsRepository extends JpaRepository<Metrics, Long> {
}
