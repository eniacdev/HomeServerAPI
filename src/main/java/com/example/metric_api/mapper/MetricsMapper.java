package com.example.metric_api.mapper;

import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.CpuInfoDto;
import com.example.metric_api.model.CpuMetricDto;
import com.example.metric_api.model.DiskMetricDto;
import com.example.metric_api.model.MemoryMetricDto;
import com.example.metric_api.model.SystemMetricsDto;
import com.example.metric_api.model.UptimeMetricDto;

/* bu, MapperClass'tır. MapStruct kullanmak yerine bu yönteme başvurmak istedim, zaten neredeyse MapperClass ve MapStruct aynı konu
   olduğundan bu konuyu öğrenmek için ilk önce MapperClass ile başlayıp ondan sonra tam otomatik işlemler için MapStruct kullanacağım.
   uzun vaade de bu sınıf çok fazla değişikliğe gidebilir...
 */

public class MetricsMapper {

    public static Metrics toEntity(SystemMetricsDto dto){

        Metrics metrics = new Metrics();

        if(dto == null)  return null;

        //CPU
        if(dto.getCpu() != null){
           // metrics.setCpuCores(dto.getCpu().getCpuCores());
            metrics.setProcessCpuLoad(dto.getCpu().getProcessCpuLoad());
            metrics.setSystemCpuLoad(dto.getCpu().getSystemCpuLoad());
            metrics.setSystemAverageLoad(dto.getCpu().getSystemAverageLoad());
            metrics.setCpuTemp(dto.getCpu().getCpuTemp());
            metrics.setCpuVolt(dto.getCpu().getCpuVolt());
            metrics.setFanSpeeds(dto.getCpu().getFanSpeeds());
        }

        //MEMORY
        if(dto.getMemory() != null){
            metrics.setMemoryUsage(dto.getMemory().getMemoryUsage());
            metrics.setFreeMemory(dto.getMemory().getFreeMemory());
            metrics.setTotalMemory(dto.getMemory().getTotalMemory());
        }

        //Disk
        if(dto.getDisk() != null){
            metrics.setDiskUsage(dto.getDisk().getDiskUsage());
            metrics.setFreeDisk(dto.getDisk().getFreeDisk());
            metrics.setTotalDisk(dto.getDisk().getTotalDisk());
        }
        

        return metrics;
    }

    // bu kodlar yeterince iyi değil. ileride refactor önemli.
    public static SystemMetricsDto toDto(Metrics metrics){
        
        SystemMetricsDto dto = new SystemMetricsDto();

        if(metrics == null) return null;

        CpuMetricDto cpu = new CpuMetricDto();
        cpu.setProcessCpuLoad(metrics.getProcessCpuLoad());
        cpu.setSystemCpuLoad(metrics.getSystemCpuLoad());
        cpu.setSystemAverageLoad(metrics.getSystemAverageLoad());
        cpu.setCpuTemp(metrics.getCpuTemp());
        cpu.setCpuVolt(metrics.getCpuVolt());
        cpu.setFanSpeeds(metrics.getFanSpeeds());
        dto.setCpu(cpu);

        
        MemoryMetricDto memory = new MemoryMetricDto();
        memory.setFreeMemory(metrics.getFreeMemory());
        memory.setMemoryUsage(metrics.getMemoryUsage());
        memory.setTotalMemory(metrics.getTotalMemory());
        dto.setMemory(memory);

        DiskMetricDto disk = new DiskMetricDto();
        disk.setDiskUsage(metrics.getDiskUsage());
        disk.setFreeDisk(metrics.getFreeDisk());
        disk.setTotalDisk(metrics.getTotalDisk());
        dto.setDisk(disk);

        UptimeMetricDto uptime = new UptimeMetricDto();
        uptime.setOsUptime(metrics.getOsUptime());
        uptime.setServiceUptime(metrics.getServiceUptime());
        dto.setOsUptime(uptime.getOsUptime());
        dto.setServiceUptime(uptime.getServiceUptime());

        return dto;
    }
}
