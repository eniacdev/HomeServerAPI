package com.example.metric_api.mapper;

import com.example.metric_api.dto.GpuInfoDto;
import com.example.metric_api.model.GpuInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class})
public interface GpuMapper {

    @Mapping(target = "vramFormatted", source = "vram", qualifiedByName = "formatBytes")
    GpuInfoDto toDto(GpuInfo gpuInfo);

    GpuInfo toEntity(GpuInfoDto gpuInfoDto);
}
