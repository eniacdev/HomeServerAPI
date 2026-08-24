package com.example.metric_api.mapper;
import com.example.metric_api.dto.SystemMetricsDto;
import com.example.metric_api.model.SystemMetrics;
import com.example.metric_api.entitiy.Metrics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/* bu, MapperClass'tır. MapStruct kullanmak yerine bu yönteme başvurmak istedim, zaten neredeyse MapperClass ve MapStruct aynı konu
   olduğundan bu konuyu öğrenmek için ilk önce MapperClass ile başlayıp ondan sonra tam otomatik işlemler için MapStruct kullanacağım.
   uzun vaade de bu sınıf çok fazla değişikliğe gidebilir...
 */
@Mapper(componentModel = "spring", uses = {
        CpuMapper.class,
        MemoryMapper.class,
        DiskMapper.class,
        NetworkMapper.class,
        UptimeMapper.class})
public interface MetricsMapper {

    @Mapping(target = "logId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Metrics toEntity(SystemMetrics systemMetrics);

    @Mapping(target = "cpu", source = "cpuMetric")
    @Mapping(target = "memory", source = "memoryMetric")
    @Mapping(target = "disk", source = "diskMetric")
    @Mapping(target = "network", source = "networkMetric")
    @Mapping(target = "uptime", source = "uptimeMetric")
    SystemMetricsDto toDto(Metrics metrics);

    SystemMetricsDto toDto(SystemMetrics systemMetrics);
}
