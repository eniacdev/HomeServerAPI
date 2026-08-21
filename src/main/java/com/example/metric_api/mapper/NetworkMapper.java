package com.example.metric_api.mapper;

import com.example.metric_api.dto.NetworkMetricDto;
import com.example.metric_api.model.NetworkMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface NetworkMapper {

    NetworkMetric toEntity(NetworkMetricDto networkMetricDto);

    @Mapping(target = "bytesRecvFormatted", source = "bytesRecv", qualifiedByName = "formatBytes")
    @Mapping(target = "bytesSentFormatted", source = "bytesSent", qualifiedByName = "formatBytes")
    NetworkMetricDto toDto(NetworkMetric networkMetric);
}
