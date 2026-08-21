package com.example.metric_api.mapper;

import com.example.metric_api.formatter.MetricFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface FormattedMappings {

    // metriklerin kendi alanlarında mapper ile formatlanması için kullanılacak.

    @Named("formatBytes")
    default String formatBytes(Long value) {
        return MetricFormatter.formatBytes(value);
    }

    @Named("formatPercentange")
    default String formatPercentange(Double value) {
        return MetricFormatter.formatPercentange(value);
    }

    @Named("formatTemperature")
    default String formatTemperature(Integer value) {
        return MetricFormatter.formatTempeture(value);
    }
}
