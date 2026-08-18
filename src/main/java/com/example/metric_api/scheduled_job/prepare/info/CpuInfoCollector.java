package com.example.metric_api.scheduled_job.prepare.info;

import com.example.metric_api.check.MetricsValidator;
import org.springframework.stereotype.Component;
import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuInfo;
import com.example.metric_api.response.ResponseType;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

@Component
public class CpuInfoCollector {
    
    public CpuInfo collectCpuInfo(){

        CpuInfo cpuInfo = new CpuInfo();
        SystemInfo si = new SystemInfo();
        CentralProcessor processor = si.getHardware().getProcessor();

        cpuInfo.setCpuName(MetricsValidator.validate(
                processor.getProcessorIdentifier().getName(),
                CpuInfoCollector.class,
                "cpuName"
        ));
        cpuInfo.setPhysicalCore(Math.toIntExact(MetricsValidator.validate(
                (long) processor.getPhysicalProcessorCount(),
                CpuInfoCollector.class,
                "physicalCore"
        )));
        cpuInfo.setLogicalCore(Math.toIntExact(MetricsValidator.validate(
                (long) processor.getLogicalProcessorCount(),
                CpuInfoCollector.class,
                "logicalCore"
        )));
        cpuInfo.setIs64Bit((processor.getProcessorIdentifier().isCpu64bit()));

        return cpuInfo;
    }
}
