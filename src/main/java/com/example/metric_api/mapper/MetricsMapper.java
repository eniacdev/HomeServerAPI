package com.example.metric_api.mapper;

import com.example.metric_api.entitiy.Metrics;
import com.example.metric_api.model.SystemMetricsDto;

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
            //metrics.setTotalMemory(dto.getMemory().getTotalMemory());
        }

        //Disk
        if(dto.getDisk() != null){
            metrics.setDiskUsage(dto.getDisk().getDiskUsage());
            metrics.setFreeDisk(dto.getDisk().getFreeDisk());
            //metrics.setTotalDisk(dto.getDisk().getTotalDisk());
        }
        

        return metrics;
    }
}
