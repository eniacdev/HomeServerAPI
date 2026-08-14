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
    Metrics toEntity(SystemMetrics systemMetricsDto);

    @Mapping(target = "cpu", source = ".")
    @Mapping(target = "memory", source = ".")
    @Mapping(target = "disk", source = ".")
    @Mapping(target = "networkMetric", source = ".")
    SystemMetricsDto toDto(Metrics metrics);

    SystemMetricsDto toDto(SystemMetrics systemMetrics);

//    public static Metrics toEntity(SystemMetricsDto model){
//
//        Metrics metrics = new Metrics();
//
//        if(model == null) return null;
//
//        // CPU
//        if(model.getCpu() != null){
//            metrics.setProcessCpuLoad(model.getCpu().getProcessCpuLoad());
//            metrics.setSystemCpuLoad(model.getCpu().getSystemCpuLoad());
//            metrics.setSystemAverageLoad(model.getCpu().getSystemAverageLoad());
//            metrics.setCpuTemp(model.getCpu().getCpuTemp());
//        }
//
//        // MEMORY
//        if(model.getMemory() != null){
//            metrics.setMemoryUsage(model.getMemory().getMemoryUsage());
//            metrics.setFreeMemory(model.getMemory().getFreeMemory());
//            metrics.setTotalMemory(model.getMemory().getTotalMemory());
//        }
//
//        // Disk
//        if(model.getDisk() != null){
//            metrics.setDiskUsage(model.getDisk().getDiskUsage());
//            metrics.setFreeDisk(model.getDisk().getFreeDisk());
//            metrics.setTotalDisk(model.getDisk().getTotalDisk());
//        }
//
//        if(model.getOsUptime() != null && model.getServiceUptime() != null){
//            metrics.setOsUptime(model.getOsUptime());
//            metrics.setServiceUptime(model.getServiceUptime() / 1000); // '/ 1000' ile saniye formatı
//        }
//
//        // Network
//        if(model.getNetworkMetric() != null){
//            metrics.setBytesRecv(model.getNetworkMetric().getBytesRecv());
//            metrics.setBytesSent(model.getNetworkMetric().getBytesSent());
//            metrics.setInErrors(model.getNetworkMetric().getInErrors());
//            metrics.setOutErrors(model.getNetworkMetric().getOutErrors());
//            metrics.setInterfaceName(model.getNetworkMetric().getInterfaceName());
//        }
//
//        return metrics;
//    }
//
//    public static SystemMetricsDto toDto(Metrics metrics){
//
//        SystemMetricsDto model = new SystemMetricsDto();
//
//        if(metrics == null) return null;
//
//        CpuMetricDto cpu = new CpuMetricDto();
//        cpu.setProcessCpuLoad(metrics.getProcessCpuLoad());
//        cpu.setSystemCpuLoad(metrics.getSystemCpuLoad());
//        cpu.setSystemAverageLoad(metrics.getSystemAverageLoad());
//        cpu.setCpuTemp(metrics.getCpuTemp());
//        model.setCpu(cpu);
//
//        MemoryMetricDto memory = new MemoryMetricDto();
//        memory.setFreeMemory(metrics.getFreeMemory());
//        memory.setMemoryUsage(metrics.getMemoryUsage());
//        memory.setTotalMemory(metrics.getTotalMemory());
//        model.setMemory(memory);
//
//        DiskMetricDto disk = new DiskMetricDto();
//        disk.setDiskUsage(metrics.getDiskUsage());
//        disk.setFreeDisk(metrics.getFreeDisk());
//        disk.setTotalDisk(metrics.getTotalDisk());
//        model.setDisk(disk);
//
//        UptimeMetricDto uptime = new UptimeMetricDto();
//        uptime.setOsUptime(metrics.getOsUptime());
//        uptime.setServiceUptime(metrics.getServiceUptime());
//        model.setOsUptime(uptime.getOsUptime());
//        model.setServiceUptime(uptime.getServiceUptime());
//
//        return model;
//    }
}
