package com.example.metric_api.mapper;

import com.example.metric_api.dto.UptimeMetricDto;
import com.example.metric_api.model.UptimeMetric;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import javax.swing.*;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface UptimeMapper {

    @Mapping(target  = "serviceUptime", source = "serviceUptime")
    @Mapping(target = "serviceUptimeFormatted", source = "serviceUptime", qualifiedByName = "formatUptime")
    @Mapping(target = "osUptime", source = "osUptime")
    @Mapping(target = "osUptimeFormatted", source = "osUptime", qualifiedByName = "formatUptime")
    UptimeMetricDto toDto(UptimeMetric uptimeMetric);

    UptimeMetric toEntity(UptimeMetricDto uptimeMetricDto);
}
