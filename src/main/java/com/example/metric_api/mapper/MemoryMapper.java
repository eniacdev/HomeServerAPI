package com.example.metric_api.mapper;

import com.example.metric_api.dto.MemoryMetricDto;
import com.example.metric_api.formatter.MetricFormatter;
import com.example.metric_api.model.MemoryMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface MemoryMapper {

    @Mapping(target = "memoryUsageFormatted", source = "memoryUsage", qualifiedByName = "formatBytes")
    @Mapping(target = "freeMemoryFormatted", source = "freeMemory", qualifiedByName = "formatBytes")
    @Mapping(target = "totalMemoryFormatted", source = "totalMemory", qualifiedByName = "formatBytes")
    MemoryMetricDto toDto(MemoryMetric memoryMetric);

    MemoryMetric toEntity(MemoryMetricDto memoryMetricDto);
}
