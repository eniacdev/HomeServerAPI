package com.example.metric_api.mapper;
import com.example.metric_api.dto.SystemMetricsResponse;
import com.example.metric_api.entitiy.Metrics;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/* bu, MapperClass'tır. MapStruct kullanmak yerine bu yönteme başvurmak istedim, zaten neredeyse MapperClass ve MapStruct aynı konu
   olduğundan bu konuyu öğrenmek için ilk önce MapperClass ile başlayıp ondan sonra tam otomatik işlemler için MapStruct kullanacağım.
   uzun vaade de bu sınıf çok fazla değişikliğe gidebilir...
 */
@Mapper(componentModel = "spring")
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
    Metrics toEntity(SystemMetricsResponse systemMetricsDto);

    @Mapping(target = "cpu", source = ".")
    @Mapping(target = "memory", source = ".")
    @Mapping(target = "disk", source = ".")
    @Mapping(target = "networkMetric", source = ".")
    SystemMetricsResponse toDto(Metrics metrics);

//    public static Metrics toEntity(SystemMetricsDto dto){
//
//        Metrics metrics = new Metrics();
//
//        if(dto == null) return null;
//
//        // CPU
//        if(dto.getCpu() != null){
//            metrics.setProcessCpuLoad(dto.getCpu().getProcessCpuLoad());
//            metrics.setSystemCpuLoad(dto.getCpu().getSystemCpuLoad());
//            metrics.setSystemAverageLoad(dto.getCpu().getSystemAverageLoad());
//            metrics.setCpuTemp(dto.getCpu().getCpuTemp());
//        }
//
//        // MEMORY
//        if(dto.getMemory() != null){
//            metrics.setMemoryUsage(dto.getMemory().getMemoryUsage());
//            metrics.setFreeMemory(dto.getMemory().getFreeMemory());
//            metrics.setTotalMemory(dto.getMemory().getTotalMemory());
//        }
//
//        // Disk
//        if(dto.getDisk() != null){
//            metrics.setDiskUsage(dto.getDisk().getDiskUsage());
//            metrics.setFreeDisk(dto.getDisk().getFreeDisk());
//            metrics.setTotalDisk(dto.getDisk().getTotalDisk());
//        }
//
//        if(dto.getOsUptime() != null && dto.getServiceUptime() != null){
//            metrics.setOsUptime(dto.getOsUptime());
//            metrics.setServiceUptime(dto.getServiceUptime() / 1000); // '/ 1000' ile saniye formatı
//        }
//
//        // Network
//        if(dto.getNetworkMetric() != null){
//            metrics.setBytesRecv(dto.getNetworkMetric().getBytesRecv());
//            metrics.setBytesSent(dto.getNetworkMetric().getBytesSent());
//            metrics.setInErrors(dto.getNetworkMetric().getInErrors());
//            metrics.setOutErrors(dto.getNetworkMetric().getOutErrors());
//            metrics.setInterfaceName(dto.getNetworkMetric().getInterfaceName());
//        }
//
//        return metrics;
//    }
//
//    public static SystemMetricsDto toDto(Metrics metrics){
//
//        SystemMetricsDto dto = new SystemMetricsDto();
//
//        if(metrics == null) return null;
//
//        CpuMetricDto cpu = new CpuMetricDto();
//        cpu.setProcessCpuLoad(metrics.getProcessCpuLoad());
//        cpu.setSystemCpuLoad(metrics.getSystemCpuLoad());
//        cpu.setSystemAverageLoad(metrics.getSystemAverageLoad());
//        cpu.setCpuTemp(metrics.getCpuTemp());
//        dto.setCpu(cpu);
//
//        MemoryMetricDto memory = new MemoryMetricDto();
//        memory.setFreeMemory(metrics.getFreeMemory());
//        memory.setMemoryUsage(metrics.getMemoryUsage());
//        memory.setTotalMemory(metrics.getTotalMemory());
//        dto.setMemory(memory);
//
//        DiskMetricDto disk = new DiskMetricDto();
//        disk.setDiskUsage(metrics.getDiskUsage());
//        disk.setFreeDisk(metrics.getFreeDisk());
//        disk.setTotalDisk(metrics.getTotalDisk());
//        dto.setDisk(disk);
//
//        UptimeMetricDto uptime = new UptimeMetricDto();
//        uptime.setOsUptime(metrics.getOsUptime());
//        uptime.setServiceUptime(metrics.getServiceUptime());
//        dto.setOsUptime(uptime.getOsUptime());
//        dto.setServiceUptime(uptime.getServiceUptime());
//
//        return dto;
//    }
}
