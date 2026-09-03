package com.example.metric_api.mapper;

import com.example.metric_api.dto.SystemInfoDto;
import com.example.metric_api.model.SystemInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {FormattedMappings.class, UptimeMapper.class, GpuMapper.class})
public interface SystemInfoMapper {

    @Mapping(target = "totalMemoryFormatted", source = "totalMemory", qualifiedByName = "formatBytes")
    @Mapping(target = "totalDiskFormatted", source = "totalDisk", qualifiedByName = "formatBytes")
    SystemInfoDto toDto(SystemInfo systemInfo);

    SystemInfo toEntity(SystemInfoDto systemInfoDto);
}
