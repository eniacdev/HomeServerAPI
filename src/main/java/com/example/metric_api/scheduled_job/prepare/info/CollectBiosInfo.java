package com.example.metric_api.scheduled_job.prepare.info;

import org.springframework.stereotype.Component;

import com.example.metric_api.exception_handler.BaseException;
import com.example.metric_api.model.BiosInfoDto;
import com.example.metric_api.response.ResponseType;

import oshi.SystemInfo;
import oshi.driver.windows.wmi.Win32Bios.BiosSerialProperty;
import oshi.hardware.Firmware;

@Component
public class CollectBiosInfo {
    

    public BiosInfoDto collectBiosInfo(){

        BiosInfoDto biosInfo = new BiosInfoDto();
        SystemInfo si = new SystemInfo();
        Firmware firmware = si.getHardware().getComputerSystem().getFirmware();

        biosInfo.setManufacturer(firmware.getManufacturer());
        biosInfo.setVersion(firmware.getVersion());
        biosInfo.setReleaseDate(firmware.getReleaseDate());
        biosInfo.setBiosName(firmware.getName());

        checkInfo(biosInfo);

        return biosInfo;
    }

    private void checkInfo(BiosInfoDto biosInfo){
        if(biosInfo.getManufacturer() == null && 
        biosInfo.getVersion() == null &&
        biosInfo.getReleaseDate() == null &&
        biosInfo.getBiosName() == null){
            throw new BaseException(ResponseType.SYSTEM_INFO_NOT_COLLECTED);
        }
    }
}
