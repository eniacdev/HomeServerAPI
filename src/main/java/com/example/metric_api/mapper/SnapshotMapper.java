package com.example.metric_api.mapper;

import com.example.metric_api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.boot.info.ProcessInfo;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = {
        CpuMapper.class,
        MemoryMapper.class,
        DiskMapper.class,
        NetworkMapper.class,
})
public interface SnapshotMapper {

    @Mapping(target = "staticMetric.cpuInfo", source = "cpuInfo")
    @Mapping(target = "staticMetric.osInfo", source = "osInfo")
    @Mapping(target = "staticMetric.networkInfo", source = "networkInfo")
    @Mapping(target = "staticMetric.biosInfo", source = "biosInfo")
    @Mapping(target = "staticMetric.motherBoardInfo", source = "motherBoardInfo")

    @Mapping(target = "currentMetric.cpuMetric", source = "cpuMetric")
    @Mapping(target = "currentMetric.memoryMetric", source = "memoryMetric")
    @Mapping(target = "currentMetric.diskMetric", source = "diskMetric")
    @Mapping(target = "currentMetric.networkMetric", source = "networkMetric")
    MetricsSnapshot toSnapshot(
            CpuMetric cpuMetric, CpuInfo cpuInfo,
            MemoryMetric memoryMetric, DiskMetric diskMetric,
            NetworkMetric networkMetric, NetworkInfo networkInfo,
            OsInfo osInfo, BiosInfo biosInfo, MotherBoardInfo motherBoardInfo, LocalDateTime generatedAt
    );
}
