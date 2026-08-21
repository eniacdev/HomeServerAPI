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
@Mapper(componentModel = "spring", uses = {CpuMapper.class})
public interface MetricsMapper {

    @Mapping(target = "logId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "processCpuLoad", source = "cpu.processCpuLoad")
    @Mapping(target = "systemCpuLoad", source = "cpu.systemCpuLoad")
    @Mapping(target = "systemAverageLoad", source = "cpu.systemAverageLoad")
    @Mapping(target = "cpuTemp", source = "cpu.cpuTemp")
    @Mapping(target = "memoryUsage", source = "memory.memoryUsage")
    @Mapping(target = "freeMemory", source = "memory.freeMemory")
    @Mapping(target = "totalMemory", source = "memory.totalMemory")
    @Mapping(target = "diskUsage", source = "disk.diskUsage")
    @Mapping(target = "freeDisk", source = "disk.freeDisk")
    @Mapping(target = "totalDisk", source = "disk.totalDisk")
    @Mapping(target = "interfaceName", source = "networkMetric.interfaceName")
    @Mapping(target = "bytesRecv", source = "networkMetric.bytesRecv")
    @Mapping(target = "bytesSent", source = "networkMetric.bytesSent")
    @Mapping(target = "inErrors", source = "networkMetric.inErrors")
    @Mapping(target = "outErrors", source = "networkMetric.outErrors")
    @Mapping(target = "serviceUptime", expression = "java(systemMetricsDto.getServiceUptime() != null ? systemMetricsDto.getServiceUptime() / 1000 : null)")
    Metrics toEntity(SystemMetrics systemMetricsDto);

    @Mapping(target = "cpu", source = ".")
    @Mapping(target = "memory", source = ".")
    @Mapping(target = "disk", source = ".")
    @Mapping(target = "networkMetric", source = ".")
    SystemMetricsDto toDto(Metrics metrics);

    SystemMetricsDto toDto(SystemMetrics systemMetrics);
}
