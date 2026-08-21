package com.example.metric_api.mapper;

import com.example.metric_api.dto.CpuMetricDto;
import com.example.metric_api.dto.DiskMetricDto;
import com.example.metric_api.model.CpuMetric;
import com.example.metric_api.model.DiskMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface CpuMapper {

    @Mapping(target = "processCpuLoadFormatted", source = "processCpuLoad", qualifiedByName = "formatPercentange")
    @Mapping(target = "systemCpuLoadFormatted", source = "systemCpuLoad", qualifiedByName = "formatPercentange")
    @Mapping(target = "systemAverageLoadFormatted", source = "systemAverageLoad", qualifiedByName = "formatPercentange")
    @Mapping(target = "cpuTemp", source = "cpuTemp", qualifiedByName = "formatTemperature")
    CpuMetricDto toDto(CpuMetric cpuMetric);
}
