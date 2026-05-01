package com.example.metric_api.scheduled_job.prepare.info;

import org.springframework.stereotype.Component;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.CpuInfoDto;
import com.example.metric_api.response.ResponseType;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;

@Component
public class CollectCpuInfo {
    
    public CpuInfoDto collectCpuInfo(){

        CpuInfoDto cpuInfo = new CpuInfoDto();
        SystemInfo si = new SystemInfo();
        CentralProcessor processor = si.getHardware().getProcessor();

        cpuInfo.setCpuName(processor.getProcessorIdentifier().getName());
        cpuInfo.setPhysicalCore(processor.getPhysicalProcessorCount());
        cpuInfo.setLogicalCore(processor.getLogicalProcessorCount());
        //cpuInfo.setMaxFreqMHz(processor.getMaxFreq() / 1_000_000); // MHz cinsinden
        cpuInfo.setIs64Bit(processor.getProcessorIdentifier().isCpu64bit());

        checkInfo(cpuInfo);

        return cpuInfo;
    }

    private void checkInfo(CpuInfoDto cpuInfo){
        if(cpuInfo.getCpuName() == null &&
        cpuInfo.getLogicalCore() == null &&
        cpuInfo.getPhysicalCore() == null &&
        /*cpuInfo.getMaxFreqMHz() == null &&*/
        cpuInfo.getIs64Bit() == null ){
            throw new BaseException(ResponseType.SYSTEM_INFO_NOT_COLLECTED);
        }
    }
}
