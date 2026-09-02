package com.example.metric_api.repository;

import com.example.metric_api.entitiy.Metrics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IMetricsRepository extends JpaRepository<Metrics, Long> {
    Page<Metrics> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
