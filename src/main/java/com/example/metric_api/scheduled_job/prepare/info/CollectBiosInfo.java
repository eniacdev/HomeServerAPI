package com.example.metric_api.scheduled_job.prepare.info;

import org.springframework.stereotype.Component;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.dto.BiosInfoResponse;
import com.example.metric_api.response.ResponseType;

import oshi.SystemInfo;
import oshi.hardware.Firmware;

@Component
public class CollectBiosInfo {
    

    public BiosInfoResponse collectBiosInfo(){

        BiosInfoResponse biosInfo = new BiosInfoResponse();
        SystemInfo si = new SystemInfo();
        Firmware firmware = si.getHardware().getComputerSystem().getFirmware();

        biosInfo.setManufacturer(firmware.getManufacturer());
        biosInfo.setVersion(firmware.getVersion());
        biosInfo.setReleaseDate(firmware.getReleaseDate());
        biosInfo.setBiosName(firmware.getName());

        checkInfo(biosInfo);

        return biosInfo;
    }

    private void checkInfo(BiosInfoResponse biosInfo){
        if(biosInfo.getManufacturer() == null && 
        biosInfo.getVersion() == null &&
        biosInfo.getReleaseDate() == null &&
        biosInfo.getBiosName() == null){
            throw new BaseException(ResponseType.SYSTEM_INFO_NOT_COLLECTED);
        }
    }
}
