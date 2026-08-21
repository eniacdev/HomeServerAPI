package com.example.metric_api.mapper;

import com.example.metric_api.dto.DiskMetricDto;
import com.example.metric_api.formatter.MetricFormatter;
import com.example.metric_api.model.DiskMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface DiskMapper {

    @Mapping(target = "diskUsageFormatted", source = "diskUsage", qualifiedByName = "formatBytes")
    @Mapping(target = "freeDiskFormatted", source = "freeDisk", qualifiedByName = "formatBytes")
    @Mapping(target = "totalDiskFormatted", source = "totalDisk", qualifiedByName = "formatBytes")
    DiskMetricDto toDto(DiskMetric diskMetric);

    DiskMetric toEntity(DiskMetricDto diskMetricDto);
}
